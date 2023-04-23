package com.educative.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Participant;
import com.educative.ecommerce.model.Server;
import com.educative.ecommerce.repository.TwoPCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwoPCService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CoordinatorService coordinatorService;

    @Autowired
    RestService restService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TwoPCRepository twoPCRepository;

    private static List<Participant> participants = new ArrayList<>();

    private List<Server> servers = new ArrayList<>();

    public void save(Participant participant) {
        twoPCRepository.save(participant);
    }

    // optimize the operation
    // initial all the Participant only when add and delete products
    private void initialParticipants() {
        List<Server> Servers = coordinatorService.serverList();
        if (participants.size() == 0) {
            for (int i = 1; i < Servers.size(); i++) {
                // the initial state is 0
                Participant curServer = new Participant(Servers.get(i), 0);
                // updateStatus(curServer, ParticipantState.READY);
                participants.add(curServer);
                twoPCRepository.save(curServer);
            }
        }
    }

    // do preparations for all participates
    public boolean prepareProduct() {
        initialParticipants();

        for (int i = 0; i < participants.size(); i++) {
            jdbcTemplate.update(
                    "UPDATE Participants SET state = ? WHERE port = ?",
                    ParticipantState.READY, participants.get(i).getPort());
        }

        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM Participants WHERE state = ?",
                Integer.class, ParticipantState.READY);

        return count == participants.size();
    }

    // Send commit delete messages to all participates
    public void commitDeleteProduct(int ProductId) {
        initialParticipants();
        for (int i = 0; i < participants.size(); i++) {
            jdbcTemplate.update(
                    "UPDATE Participants SET state = ? WHERE port = ?",
                    ParticipantState.COMMIT, participants.get(i).getPort());
        }
        productService.delete(ProductId);
    }

    // Send commit add messages to all participates
    public void commitAddProduct(ProductDto productDto, Category category) throws Exception {
        initialParticipants();
        for (int i = 0; i < participants.size(); i++) {
            jdbcTemplate.update(
                    "UPDATE Participants SET state = ? WHERE port = ?",
                    ParticipantState.COMMIT, participants.get(i).getPort());
        }
        productService.createProduct(productDto, category);
    }

    // Rollback the transaction if any of the participates are not ready
    public boolean abortProduct() {
        String sql = "UPDATE Participants SET state = ? ";
        int rowCount = jdbcTemplate.update(sql, ParticipantState.ABORT);
        return rowCount == 1;
    }
}

class ProductState {
    public static final int READY = 0;
    public static final int COMMIT = 1;
    public static final int ABORT = 2;
}

class ParticipantState {
    public static final int READY = 100;
    public static final int COMMIT = 200;
    public static final int ABORT = -100;
}

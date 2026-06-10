package pk.ms.pasir_siwak_mateusz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import pk.ms.pasir_siwak_mateusz.model.Transaction;
import pk.ms.pasir_siwak_mateusz.model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);
    List<Transaction> findByUser(User user);
    List<Transaction> findByUserAndTimestampGreaterThanEqual(User user, LocalDateTime timestamp);
}
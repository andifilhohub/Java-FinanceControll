package com.financeapi.repository;

import com.financeapi.model.Expenditures;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface ExpendituresRepository extends JpaRepository<Expenditures, Long> {
    
    Optional<Expenditures> findByAmountAndCompanyNameAndCategoryAndDescriptionAndDateBetween(
        BigDecimal amount, 
        String companyName, 
        String category, 
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime
    );

    // Soma total dos gastos
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expenditures e")
    BigDecimal getTotalExpenditures();

    // Soma dos gastos apenas do dia atual
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expenditures e WHERE DATE(e.date) = :date")
    BigDecimal getTotalExpendituresToday(@Param("date") LocalDate date);



}

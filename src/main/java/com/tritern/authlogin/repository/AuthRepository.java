package com.tritern.authlogin.repository;

//import com.tritern.authlogin.PasswordSalt;
import com.tritern.authlogin.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Integer> {

    public boolean existsByEmailAndUsername(String Email, String Username);

    public boolean existsByEmailAndPassword(String Email, String Password);

    public boolean existsById(int id);

    @Query(value = "SELECT * FROM Login WHERE FirstName LIKE %?1%"
            + " OR UserName LIKE %?1%"
            + " OR EmailID LIKE %?1%"
            , nativeQuery = true)
    public List<AuthEntity> search(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM Login WHERE FirstName LIKE %?1%"
            + " OR UserName LIKE %?1%"
            + " OR EmailID LIKE %?1%"
            , nativeQuery = true)
    public List<AuthEntity> searchLength(String keyword);

    AuthEntity findByEmail(String user);

    public boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM login WHERE EmailID=?1",nativeQuery = true)
    AuthEntity findbyemail(String email);
}

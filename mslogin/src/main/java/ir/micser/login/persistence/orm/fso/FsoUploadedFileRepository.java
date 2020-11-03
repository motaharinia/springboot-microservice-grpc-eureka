package ir.micser.login.persistence.orm.fso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FsoUploadedFileRepository extends JpaRepository<FsoUploadedFile, Integer> {
    FsoUploadedFile findByFileKey(String fileKey);
}

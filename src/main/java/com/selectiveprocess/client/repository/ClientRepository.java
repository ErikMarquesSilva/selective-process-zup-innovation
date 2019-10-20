package com.selectiveprocess.client.repository;

import com.selectiveprocess.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByCpf(Long clientCpf);

}

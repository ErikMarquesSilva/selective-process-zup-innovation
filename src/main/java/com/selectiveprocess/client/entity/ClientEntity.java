package com.selectiveprocess.client.entity;

import com.selectiveprocess.client.interfaces.Client;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client")
public class ClientEntity extends AbstractPersistable<Long> {

    @Id
    private Long id;
    private String name;
    private Long cpf;
    private String dateOfBirth;
    private String address;

    public ClientEntity(Client.ClientRequest request) {
        this.id = request.getId();
        this.name = request.getName();
        this.cpf = request.getCpf();
        this.dateOfBirth = request.getDateOfBirth();
        this.address = request.getAddress();
    }

    public ClientEntity(Long id) {
        this.id = id;
    }

    public ClientEntity(String name, Long cpf, String dateOfBirth, String address) {
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}

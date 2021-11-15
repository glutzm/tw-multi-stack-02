package br.com.treinaweb.ediaristas.models;

import br.com.treinaweb.ediaristas.converters.UserInfoConverter;
import br.com.treinaweb.ediaristas.web.controllers.FileController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Diarista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @CPF
    @NotNull
    @Size(min = 11, max = 14)
    @Column(nullable = false, unique = true, length = 11)
    @Convert(converter = UserInfoConverter.class)
    @JsonIgnore
    private String cpf;

    @Email
    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String email;

    @NotNull
    @Size(min = 11, max = 15)
    @Column(nullable = false, length = 11)
    @Convert(converter = UserInfoConverter.class)
    @JsonIgnore
    private String telefone;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    @JsonIgnore
    private String logradouro;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    @JsonIgnore
    private String numero;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    @JsonIgnore
    private String bairro;

    @JsonIgnore
    private String complemento;

    @NotNull
    @Size(min = 8, max = 9)
    @Column(nullable = false, length = 8)
    @Convert(converter = UserInfoConverter.class)
    @JsonIgnore
    private String cep;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(nullable = false, length = 2)
    @JsonIgnore
    private String estado;

    @Column(nullable = false)
    @JsonIgnore
    private String codigoIbge;

    @Column(nullable = false)
    @JsonIgnore
    private String foto;

    @JsonProperty("foto_usuario")
    public String getFotoUrl() throws IOException {
        return linkTo(methodOn(FileController.class).file(this.foto)).toString();
    }

    @JsonProperty("reputacao")
    public Integer getReputacao() {
        return ThreadLocalRandom.current().nextInt(0, 6);
    }
}

package br.com.treinaweb.ediaristas.models;

import br.com.treinaweb.ediaristas.converters.UserInfoConverter;
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
    private String nomeCompleto;

    @CPF
    @NotNull
    @Size(min = 11, max = 14)
    @Column(nullable = false, unique = true, length = 11)
    @Convert(converter = UserInfoConverter.class)
    private String cpf;

    @Email
    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 11, max = 15)
    @Column(nullable = false, length = 11)
    @Convert(converter = UserInfoConverter.class)
    private String telefone;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String logradouro;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String numero;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String bairro;

    private String complemento;

    @NotNull
    @Size(min = 8, max = 9)
    @Column(nullable = false, length = 8)
    @Convert(converter = UserInfoConverter.class)
    private String cep;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false)
    private String codigoIbge;

    @Column(nullable = false)
    private String foto;
}

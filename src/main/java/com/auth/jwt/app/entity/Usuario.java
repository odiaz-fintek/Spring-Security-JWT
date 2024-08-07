package com.auth.jwt.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Esta clase representa a la tabla de la BD llamada <b>usuarios</b>
 * en la cual las propiedades definidas aqui seran mapeadas a la tabla.
 * Ya que existe una relacion de Muchos a Muchos con la Tabla roles se genera la relacion
 * @ManyToMany con la tabla {@link Role}.
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    /* ~ Propiedades
    ==================================== */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotNull
    private String username;

    @NotNull
    private String correo;

    @NotNull
    private String password;

    @Column(length = 512)
    @Getter @Setter
    private String token;

    @Getter @Setter
    private LocalDateTime tokenExpirationDate;

    private boolean activo;

    /* Conteno de intentos*/
    @Getter @Setter
    private boolean accountNonLocked = true;

    @Getter @Setter
    private int failedAttempt = 0;

    @Getter @Setter
    private Date lockTime;

    @NotNull @Getter @Setter
    private String apikey;

    /*----------References de usuarios y rol -----------------*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = {@JoinColumn(name = "id_usuario")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    private List<Role> roles;
    /*----------References de usuarios y producto -----------------*/
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "usuario_producto",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "producto_id")}
    )
    private Set<Producto> productos = new HashSet<>();
    /*
    *   Set es una colección que no permite elementos duplicados
    *   Usuario no tenga el mismo Producto asociado más de una vez.
    *
    *   HashSet es una implementación de la interfaz Set que utiliza
    *   una tabla hash para almacenar los elementos.
    *
    * */


    /* ~ Metodos
    ==================================== */
    public Usuario(){
        roles = new ArrayList<Role>();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void agregarRoleALista(Role role){
        this.roles.add(role);
    }

} // fin de la clase entidad

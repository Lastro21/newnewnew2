package com.example.newnewnew.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(name = "changeBackground_color",  query = "UPDATE user SET background_color=? WHERE user_id=?", resultClass = User.class),
@NamedNativeQuery(name = "changeBackground_block",  query = "UPDATE user SET background_block=? WHERE user_id=?", resultClass = User.class),
@NamedNativeQuery(name = "changeFont_color",        query = "UPDATE user SET font_color=?       WHERE user_id=?", resultClass = User.class),
@NamedNativeQuery(name = "changeBlock_widht",       query = "UPDATE user SET block_widht=?      WHERE user_id=?", resultClass = User.class),
@NamedNativeQuery(name = "changeBlock_height",      query = "UPDATE user SET block_height=?     WHERE user_id=?", resultClass = User.class)
})
@Table (name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;


	@Column(name = "email")
	@Email(message = "*Укажите правильный Email")
	@NotEmpty(message = "*Поле не может быть пустым")
	@Length(min = 7, message = "*Слишком короткий Email")
	private String email;


	@Column(name = "password")
	@Length(min = 3, message = "*Длина менее 3 символов")
	@NotEmpty(message = "*Поле не может быть пустым")

	private String password;



	///f
	@Column(name = "confirm_password")
	@NotEmpty(message = "*Поле не может быть пустым")
	@Length(min = 3, message = "*Длина менее 3 символов")
	private String confirmPassword;


	@Column(name = "name")
	@NotEmpty(message = "*Поле не может быть пустым")
	@Length(min = 2, message = "*Длина менее 2 символов")
	@Pattern(regexp="^[_a-zA-Z]+$", message="В имени должны быть только латинские буквы")
	private String name;


	@Column(name = "last_name")
	@NotEmpty(message = "*Поле не может быть пустым")
	private String lastName;




/*     ////////////////////     Начинаются поля для стилей     /////////////////////      */

	@Column(name = "background_color")
	private String background_color;

	@Column(name = "background_block")
	private String background_block;

	@Column(name = "font_color")
	private String font_color;

	@Column(name = "block_widht")
	private String block_widht;

	@Column(name = "block_height")
	private String block_height;


/*     ////////    Заканчиваются поля для стилей    //////////////   */





	@Column(name = "active")
	private int active;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}





/*     ////////    Заканчиваются поля для стилей    //////////////   */





	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getBackground_block() {
		return background_block;
	}

	public void setBackground_block(String background_block) {
		this.background_block = background_block;
	}

	public String getFont_color() {
		return font_color;
	}

	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}

	public String getBlock_widht() {
		return block_widht;
	}

	public void setBlock_widht(String block_widht) {
		this.block_widht = block_widht;
	}

	public String getBlock_height() {
		return block_height;
	}

	public void setBlock_height(String block_height) {
		this.block_height = block_height;
	}



}

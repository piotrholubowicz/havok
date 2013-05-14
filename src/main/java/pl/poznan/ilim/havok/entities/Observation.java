package pl.poznan.ilim.havok.entities;
// Generated 2011-02-04 09:10:43 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import net.sf.gilead.pojo.gwt.LightEntity;

/**
 * Observation generated by hbm2java
 */
@Entity
@Table(name = "observations", catalog = "havok", uniqueConstraints = @UniqueConstraint(columnNames = {
		"serial", "id_status", "id_item" }))
public class Observation extends LightEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5145668203153350106L;
	private Integer id;
	private Item item;
	private Status status;
	private String serial;
	private int quantity;
	private String comment;
	private Float discount;
	private Date date;

	public Observation() {
	}

	public Observation(Item item, Status status, int quantity, Date date) {
		this.item = item;
		this.status = status;
		this.quantity = quantity;
		this.date = date;
	}

	public Observation(Item item, Status status, String serial, int quantity,
			String comment, Float discount, Date date) {
		this.item = item;
		this.status = status;
		this.serial = serial;
		this.quantity = quantity;
		this.comment = comment;
		this.discount = discount;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_item", nullable = false)
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "serial", length = 20)
	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "comment", length = 200)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "discount", precision = 12, scale = 0)
	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

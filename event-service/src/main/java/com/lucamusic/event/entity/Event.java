package com.lucamusic.event.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Data
@Document(value = "events") @Builder @AllArgsConstructor @NoArgsConstructor
public class Event {
	@Id
	private String id;
	@NotNull(message = "El campo nombre debe existir y no ser vacío") @NotBlank
	private String name;
	@NotNull(message = "El campo shortDescription debe existir")
	private String shortDescription;
	private String longDescription;
	private String photoUrl;
	@Future(message = "La fecha introducida ya ha pasado") @NotNull(message = "El campo date debe existir") @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	@NotNull (message = "El campo location debe existir") @Valid
	private Location location;
	Map<String, Double> prices;
	@NotNull (message = "El campo musicStyle debe existir")
	private String musicStyle;
	@PositiveOrZero(message = "El valor debe ser mayor o igual de 0")
	private Integer ticketsSold = 0;
	private String status;
}

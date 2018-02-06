package io.manasobi.messaging.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Entity
public class Rate extends BaseEntity {

	@Id
    @NonNull
	private String code;

	@NonNull
	private Float rate;

    @NonNull
	@JsonIgnore
	private LocalDate date;
}


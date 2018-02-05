package io.manasobi.messaging.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

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


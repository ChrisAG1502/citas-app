package azc.uam.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CitaDisponibleDTO {
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private boolean existing;

    public CitaDisponibleDTO(LocalDateTime start, LocalDateTime end, String title, boolean existing) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.existing = existing;
    }

}
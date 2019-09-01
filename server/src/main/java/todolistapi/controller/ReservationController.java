package todolistapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import todolistapi.entity.Reservation;
import todolistapi.repository.ReservationRepository;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;


    @GetMapping("/reservations")
    public Collection<Reservation> getReservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/reservations/{reservationID}")
    public Reservation getReservation(@PathVariable Long reservationID) {
        return reservationRepository.findById(reservationID).get();
    }

    @DeleteMapping("/reservations/{reservationID}")
    public void deleteReservation(@PathVariable long reservationID) {
        reservationRepository.deleteById(reservationID);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Object> createReservation(@RequestBody Reservation reservation) {
        Reservation saveReservation = reservationRepository.save(reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveReservation.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/reservations/{reservationID}")
    public ResponseEntity<Object> updateReservation(@RequestBody Reservation reservation, @PathVariable long reservationID) {

        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationID);

        if (!reservationOptional.isPresent())
            return ResponseEntity.notFound().build();

        reservation.setId(reservationID);

        reservationRepository.save(reservation);

        return ResponseEntity.noContent().build();
    }


}
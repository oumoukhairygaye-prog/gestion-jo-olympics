package sn.jo.gestion.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import sn.jo.gestion.dto.AthleteResponseDTO;
import sn.jo.gestion.service.AthleteService;
import sn.jo.gestion.soap.generated.Athlete;
import sn.jo.gestion.soap.generated.GetAthleteRequest;
import sn.jo.gestion.soap.generated.GetAthleteResponse;

@Endpoint
@RequiredArgsConstructor
public class AthleteEndpoint {

    private static final String NAMESPACE_URI = "http://gestion.jo.sn/soap";

    private final AthleteService athleteService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAthleteRequest")
    @ResponsePayload
    public GetAthleteResponse getAthlete(@RequestPayload GetAthleteRequest request) {
        AthleteResponseDTO dto = athleteService.findById(request.getId());

        Athlete athlete = new Athlete();
        athlete.setId(dto.getId());
        athlete.setNom(dto.getNom());
        athlete.setPrenom(dto.getPrenom());
        athlete.setSexe(dto.getSexe() != null ? dto.getSexe().name() : null);
        athlete.setNationalite(dto.getNationalite());
        athlete.setDiscipline(dto.getDiscipline());

        GetAthleteResponse response = new GetAthleteResponse();
        response.setAthlete(athlete);
        return response;
    }

}

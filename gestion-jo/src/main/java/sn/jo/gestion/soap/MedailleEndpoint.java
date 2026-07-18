package sn.jo.gestion.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import sn.jo.gestion.dto.MedailleTableauDTO;
import sn.jo.gestion.service.MedailleService;
import sn.jo.gestion.soap.generated.GetTableauMedaillesRequest;
import sn.jo.gestion.soap.generated.GetTableauMedaillesResponse;
import sn.jo.gestion.soap.generated.LigneMedaille;

@Endpoint
@RequiredArgsConstructor
public class MedailleEndpoint {

    private static final String NAMESPACE_URI = "http://gestion.jo.sn/soap";

    private final MedailleService medailleService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTableauMedaillesRequest")
    @ResponsePayload
    public GetTableauMedaillesResponse getTableauMedailles(@RequestPayload GetTableauMedaillesRequest request) {
        GetTableauMedaillesResponse response = new GetTableauMedaillesResponse();

        for (MedailleTableauDTO dto : medailleService.getTableauMedailles()) {
            LigneMedaille ligne = new LigneMedaille();
            ligne.setPays(dto.getPays());
            ligne.setOr(dto.getOr());
            ligne.setArgent(dto.getArgent());
            ligne.setBronze(dto.getBronze());
            ligne.setTotal(dto.getTotal());
            response.getLigne().add(ligne);
        }

        return response;
    }

}

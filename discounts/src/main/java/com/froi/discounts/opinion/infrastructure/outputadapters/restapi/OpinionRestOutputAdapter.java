package com.froi.discounts.opinion.infrastructure.outputadapters.restapi;

import com.froi.discounts.common.exceptions.NetworkMicroserviceException;
import com.froi.discounts.opinion.infrastructure.outputports.restapi.FindDishOutputPort;
import com.froi.discounts.opinion.infrastructure.outputports.restapi.FindRoomOutputPort;
import com.froi.discounts.opinion.infrastructure.outputports.restapi.FindUserOutputPort;
import org.hibernate.annotations.CollectionId;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class OpinionRestOutputAdapter implements FindDishOutputPort, FindRoomOutputPort, FindUserOutputPort {

    @Override
    public boolean existsDish(String dishId) throws NetworkMicroserviceException {
        String url = "http://localhost:8083/restaurants/v1/dishes/exists/" + dishId;
        System.out.println(url);
        return findEntity(url);
    }

    @Override
    public boolean existsRoom(String room, String hotelId) throws NetworkMicroserviceException {
        String url = "http://localhost:8082/hotels/v1/rooms/exists/" + room + "/" + hotelId;
        return findEntity(url);
    }

    @Override
    public boolean existsUser(String user) {
        return false;
    }

    private boolean findEntity(String url) throws NetworkMicroserviceException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Void> response = restTemplate.exchange(
                    url,
                    HttpMethod.HEAD,
                    requestEntity,
                    Void.class
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            throw new NetworkMicroserviceException(e.getMessage());
        } catch (Exception e) {
            throw new NetworkMicroserviceException(e.getMessage());
        }
    }

}

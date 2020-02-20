package vividseat.hlgo.app;

import vividseat.hlgo.business.BusinessException;
import vividseat.hlgo.business.ServiceBusiness;
import vividseat.hlgo.utils.Constants;
import vividseat.hlgo.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "Find the Celebrity")
public class ServiceController {

    @Autowired
    ServiceBusiness serviceBusiness;

    @RequestMapping(value = "/getAllPeople", method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(  value = "Retrieve people.",
            response = String.class)
    @ApiResponses( {
            @ApiResponse(code = 200, message = "OK",  response = List.class),
            @ApiResponse(code = 400, message = "Bad Request",  response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error",  response = String.class)
    } )
    @GetMapping("/response-entity-builder-with-http-headers")
    public ResponseEntity<List<Person>> getAllPeople(){

        ResponseEntity<List<Person>> response;

        try {
            List<Person> businessResult = serviceBusiness.getAllPersons();
            response = ResponseEntity.ok(businessResult);
        } catch (BusinessException ex) {
            ex.printStackTrace();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(Constants.HTTP_HEADER_ERR_MSG_FIELD_NAME, "An error has ocurred at business layer. " + ex.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(httpHeaders).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(Constants.HTTP_HEADER_ERR_MSG_FIELD_NAME, ex.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(httpHeaders).body(null);
        }

        return response;
    }

    @RequestMapping(value = "/getTheCelebrity", method = RequestMethod.GET,
            produces = "application/json")
    @ApiOperation(  value = "Retrieve the celebrity.",
            response = String.class)
    @ApiResponses( {
            @ApiResponse(code = 200, message = "OK",  response = List.class),
            @ApiResponse(code = 400, message = "Bad Request",  response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error",  response = String.class)
    } )
    @GetMapping("/response-entity-builder-with-http-headers")
    public ResponseEntity<Person> getTheCelebrity(){

        ResponseEntity<Person> response;

        try {
            List<Person> businessResult = serviceBusiness.getAllPersons();
            response = ResponseEntity.ok(businessResult.stream()
                            .filter(p -> p.isACelebrity())
                            .findAny().orElse(null));
        } catch (BusinessException ex) {
            ex.printStackTrace();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(Constants.HTTP_HEADER_ERR_MSG_FIELD_NAME, "An error has ocurred at business layer. " + ex.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(httpHeaders).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(Constants.HTTP_HEADER_ERR_MSG_FIELD_NAME, ex.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(httpHeaders).body(null);
        }

        return response;
    }

    /* Para las operaciones de update y remove, desarrollaría métodos PUT y DELETE respectivamente */
}

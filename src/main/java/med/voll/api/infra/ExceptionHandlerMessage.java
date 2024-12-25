package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerMessage {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity trataErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroValidacao(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosMensagemValidacao::new).toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErroDeDataIntegration(DataIntegrityViolationException exception){
        var erro = exception.getCause().getCause().getLocalizedMessage();
        return ResponseEntity.internalServerError().body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity tratarArgumentException(MethodArgumentTypeMismatchException exception){
        var mensagemErro = exception.getLocalizedMessage();
        return ResponseEntity.badRequest().body(mensagemErro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErroMessageNotReadable(HttpMessageNotReadableException exception){
        var mensagemErro = exception.getMessage();
        return ResponseEntity.badRequest().body(mensagemErro);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity tratarErroNullpointer(NullPointerException exception){
        var mensagemErro = exception.getLocalizedMessage();
        return ResponseEntity.internalServerError().body(mensagemErro);
    }

    private record DadosMensagemValidacao(String campo, String mensagem){
        public DadosMensagemValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}

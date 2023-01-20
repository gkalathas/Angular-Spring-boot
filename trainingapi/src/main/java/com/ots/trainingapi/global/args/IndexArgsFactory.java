package com.ots.trainingapi.global.args;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Factory δημιουργίας αντικειμένων dto για τα ορίσματα αναζήτησης
 * <p>Συγκέντρωση επαναλαμβανόμενου κώδικα σε ένα κοινό σημείο.</p>
 */
@Component
public class IndexArgsFactory {
    
    private static ObjectMapper objectMapper;
    
    @Autowired
    public IndexArgsFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    /**
     * Δημιουργία αντικειμένου ορισμάτων αναζήτησης με βάση κείμενο json
     * <p>Για το deserialization χρησιμοποιείται ο {@link ObjectMapper}.</p>
     * @param valueType    Τύπος αντικειμένου που θα δημιουργηθεί
     * @param searchString Κείμενο ορισμάτων ως json
     * @param <T>
     * @return Αντικείμενο ορισμάτων με αρχικοποιημένες τιμές στα σχετικά πεδία
     */
    public static <T> T createArgs(Class<T> valueType, String searchString) {
        
        try {
            return objectMapper.readValue(searchString, valueType);
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

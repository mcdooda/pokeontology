package pokeontology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import java.io.InputStream;

/**
 *
 * @author dodelien
 */
public abstract class AbstractModel {
    protected Model model = ModelFactory.createDefaultModel();
    
    public AbstractModel(String fileName) {
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + fileName + " not found");
        }
        model.read(in, null, "TTL");
    }
}

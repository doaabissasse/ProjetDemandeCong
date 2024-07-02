package leaveapp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class salarierDAO {

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public salarierDAO() {
        this.database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("employees");
    }

    public void save(salarier salarier) {
        Document document = new Document("nom", salarier.getNom())
                .append("prenom", salarier.getPrenom())
                .append("age", salarier.getAge())
                .append("date_naissance", salarier.getDateNaissance().toString())
                .append("sexe", salarier.getSexe())
                .append("adresse", new Document("rue", salarier.getAdresse().getRue())
                    .append("ville", salarier.getAdresse().getVille())
                    .append("code_postal", salarier.getAdresse().getCodePostal())
                    .append("pays", salarier.getAdresse().getPays()))
                .append("email", salarier.getEmail())
                .append("telephone", salarier.getTelephone())
                .append("poste", salarier.getPoste())
                .append("departement", salarier.getDepartement())
                .append("date_embauche", salarier.getDateEmbauche().toString())
                .append("salaire",  salarier.getSalaire())
                .append("conges", salarier.getConges().stream().map(c -> new Document("type", c.getType())
                    .append("date_debut", c.getDateDebut().toString())
                    .append("date_fin", c.getDateFin().toString())
                    .append("statut", c.getStatut())).toList());

        collection.insertOne(document);
    }

    public List<salarier> findAll() {
        List<salarier> employees = new ArrayList<>();
        for (Document doc : collection.find()) {
        	salarier employee = new salarier();
            employee.setId(doc.getObjectId("_id"));
            employee.setNom(doc.getString("nom"));
            employee.setPrenom(doc.getString("prenom"));
            employee.setAge(doc.getInteger("age"));
            employee.setDateNaissance(LocalDate.parse(doc.getString("date_naissance")));
            employee.setSexe(doc.getString("sexe"));

            Document addressDoc = doc.get("adresse", Document.class);
            Adresse adresse = new Adresse();
            adresse.setRue(addressDoc.getString("rue"));
            adresse.setVille(addressDoc.getString("ville"));
            adresse.setCodePostal(addressDoc.getString("code_postal"));
            adresse.setPays(addressDoc.getString("pays"));
            employee.setAdresse(adresse);

            employee.setEmail(doc.getString("email"));
            employee.setTelephone(doc.getString("telephone"));
            employee.setPoste(doc.getString("poste"));
            employee.setDepartement(doc.getString("departement"));
            employee.setDateEmbauche(LocalDate.parse(doc.getString("date_embauche")));
            employee.setSalaire(doc.getInteger("salaire"));
            List<Conge> conges = new ArrayList<>();
            for (Document congeDoc : doc.getList("conges", Document.class)) {
                Conge conge = new Conge();
                conge.setType(congeDoc.getString("type"));
                conge.setDateDebut(LocalDate.parse(congeDoc.getString("date_debut")));
                conge.setDateFin(LocalDate.parse(congeDoc.getString("date_fin")));
                conge.setStatut(congeDoc.getString("statut"));
                conges.add(conge);
            }
            employee.setConges(conges);

            employees.add(employee);
        }
        return employees;
    }
}


package problem4;

import org.hibernate.service.spi.ServiceException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiseaseInfoService  {

    static Map<String, Integer> name2Id = new HashMap<String, Integer>();
    static Map<Integer, DiseaseInfo> id2Info = new HashMap<Integer, DiseaseInfo>();

    static int VCF_COLUMN_INDEX = 3;
    static int MSF_COLUMN_INDEX = 10;

    static {
        //AnnotationFactory.initDiseaseInfo(name2Id, id2Info);
    }

    private DocumentDao documentDao;

    public DiseaseInfoService(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    public DiseaseInfo getDiseaseInfoByName(String name) {
        Integer diseaseId = name2Id.get(name);
        return (diseaseId != null) ? id2Info.get(diseaseId) : null;
    }

    public Set<Disease> getDiseasesForDataset(int datasetId) throws ServiceException {
        try {

            Set<Disease> diseases = new HashSet<>();

            DataSet ds = documentDao.getDocument(datasetId);
            if (ds.getType()== DataSet.Type.VCF) {
                appendDiseases(diseases, ds, VCF_COLUMN_INDEX);
            } else if (ds.getType() == DataSet.Type.MSF) {
                appendDiseases(diseases, ds, MSF_COLUMN_INDEX);
            }

            return diseases;

        } catch(Exception e) {
            throw new ServiceException ("Error processing dataset.", e);
        }
    }

    private void appendDiseases(
            Set<Disease> diseases,
            DataSet ds,
            int columnIndex) {

        for (int i = 0; i < ds.getRowCount(); i++) {
            DataSetRow row = ds.getRow(i);
            String name = row.getColumn(columnIndex);

            Integer diseaseId = name2Id.get(name);
            diseases.add(mapToDisease(id2Info.get(diseaseId)));
        }
    }

    private Disease mapToDisease(DiseaseInfo diseaseInfo) {

        return new Disease(diseaseInfo.getName(), diseaseInfo.getId());
    }
}


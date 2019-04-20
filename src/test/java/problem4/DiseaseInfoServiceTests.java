package problem4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class DiseaseInfoServiceTests {

    private static String diseaseName = "trgewqd";
    private static Integer diseaseId = 23;

    private DiseaseInfoService diseaseInfoService;
    private DocumentDao documentDaoMock;

    @Before
    public void setup() {

        documentDaoMock = mock(DocumentDao.class);
        diseaseInfoService = new DiseaseInfoService(documentDaoMock);

        DiseaseInfoService.name2Id.put(diseaseName, diseaseId);
        DiseaseInfoService.id2Info.put(diseaseId, new DiseaseInfo(diseaseName, diseaseId));
    }

    @Test
    public void getDiseaseInfoByName_whenNameIsNull_returnNull() {

        // arrange
        String diseaseName = null;

        // act
        DiseaseInfo result = diseaseInfoService.getDiseaseInfoByName(diseaseName);

        // assert
        assertThat(result).isNull();
    }

    @Test
    public void getDiseaseInfoByName_whenDiseaseDoesNotExist_returnNull() {

        // arrange
        String nonexistentDisease = "dsfvdfesd";

        // act
        DiseaseInfo result = diseaseInfoService.getDiseaseInfoByName(nonexistentDisease);

        // assert
        assertThat(result).isNull();
    }

    @Test
    public void getDiseaseInfoByName_whenDiseaseExists_returnDisease() {

         // act
        DiseaseInfo result = diseaseInfoService.getDiseaseInfoByName(diseaseName);

        // assert
        assertThat(result.getId()).isEqualTo(diseaseId);
        assertThat(result.getName()).isEqualTo(diseaseName);
    }

    @Test
    @Parameters(method = "getScenarios")
    public void getDiseasesForDataset_whenDatasetHasTypeVCF_returnExpectedData(int columnIndex, DataSet.Type type) {

        // arrange
        int datasetId = 34;
        DataSetRow dataSetRowMock = mock(DataSetRow.class);
        when(dataSetRowMock.getColumn(columnIndex)).thenReturn(diseaseName);
        DataSet dataSetMock = mock(DataSet.class);
        when(dataSetMock.getType()).thenReturn(type);
        when(dataSetMock.getRowCount()).thenReturn(1);
        when(dataSetMock.getRow(0)).thenReturn(dataSetRowMock);
        when(documentDaoMock.getDocument(datasetId)).thenReturn(dataSetMock);

        // act
        Set<Disease> result = diseaseInfoService.getDiseasesForDataset(datasetId);

        // assert
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).allMatch(d -> d.getId() == diseaseId && d.getName().equals(diseaseName));
    }

    private Object[] getScenarios() {

        return new Object[] {
                new Object[] {DiseaseInfoService.VCF_COLUMN_INDEX, DataSet.Type.VCF},
                new Object[] {DiseaseInfoService.VCF_COLUMN_INDEX, DataSet.Type.VCF}
        };
    }
}

package problem4;

public class DataSet {

    private Type type;
    private int rowCount;

    public Type getType() {

        return type;
    }

    public DataSetRow getRow(int i) {

        return null;
    }

    public int getRowCount() {

        return rowCount;
    }

    public enum Type {
        VCF(0),
        MSF(1);

        private final int value;

        Type(int value) {

            this.value = value;
        }

        public int getValue() {

            return value;
        }
    }
}

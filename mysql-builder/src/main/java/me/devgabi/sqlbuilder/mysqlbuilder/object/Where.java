package me.devgabi.sqlbuilder.mysqlbuilder.object;

public final class Where {

    private final String column;
    private final String operator;
    private final Object value;

    public Where(final String column, final String operator, final Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public String getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }
}

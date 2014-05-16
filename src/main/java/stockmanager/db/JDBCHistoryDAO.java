package stockmanager.db;

import org.h2.jdbcx.JdbcConnectionPool;
import stockmanager.domain.products.Cow;
import stockmanager.domain.products.Pig;
import stockmanager.domain.products.Product;
import stockmanager.domain.products.Sheep;
import stockmanager.domain.stock.Warehouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum JDBCHistoryDAO {
    INSTANCE;
    private Statement stmt = null;
    private PreparedStatement prepStmt = null;
    private JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:~/test;INIT=RUNSCRIPT from '~/create.sql'", "", "");
    private Connection connection = null;

    private JDBCHistoryDAO() {
    }

    public void setBeforeValuesInDatabase(Integer[] beforeValues) {
        //String sql = "UPDATE HISTORY SET " + columnNames[i] + "=" + beforeValues[i] + " WHERE ID = (SELECT ID FROM (SELECT * FROM HISTORY ORDER BY ID DESC) WHERE ROWNUM <= 1)";
        String sql = "INSERT INTO HISTORY (COW_BEFORE, SHEEP_BEFORE, PIG_BEFORE) VALUES (?, ?, ?)";
        try {
            connection = connectionPool.getConnection();
            prepStmt = connection.prepareStatement(sql);

            prepStmt.setInt(1, beforeValues[0]);
            prepStmt.setInt(2, beforeValues[1]);
            prepStmt.setInt(3, beforeValues[2]);
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prepStmt != null) {
                try {
                    prepStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setAfterValuesInDatabase() {
        String[] columnNames = new String[]{"COW_AFTER", "SHEEP_AFTER", "PIG_AFTER"};
        Integer[] afterValues = new Integer[]{(int) Warehouse.INSTANCE.getQty(Cow.INSTANCE), (int) Warehouse.INSTANCE.getQty(Sheep.INSTANCE), (int) Warehouse.INSTANCE.getQty(Pig.INSTANCE)};
        try {
            connection = connectionPool.getConnection();
            stmt = connection.createStatement();
            for (int i = 0; i < 3; i++) {
                String sql = "UPDATE HISTORY SET " + columnNames[i] + "=" + afterValues[i] + " WHERE ID = (SELECT ID FROM (SELECT * FROM HISTORY ORDER BY ID DESC) WHERE ROWNUM <= 1)";
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Long> getLastBeforeValues() {
        ResultSet resultSetBefore;
        List<Long> before = new ArrayList<>();
        String sql1 = "SELECT COW_BEFORE, SHEEP_BEFORE, PIG_BEFORE FROM HISTORY WHERE ID = (SELECT ID FROM (SELECT * FROM HISTORY ORDER BY ID DESC) WHERE ROWNUM <= 1)";
        try {
            connection = connectionPool.getConnection();
            stmt = connection.createStatement();

            resultSetBefore = stmt.executeQuery(sql1);
            while (resultSetBefore.next()) {
                for (int i = 1; i <= 3; i++) {
                    before.add((long) resultSetBefore.getInt(i));
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return before;
    }

    public List<Long> getLastButOneAfterValues() {
        ResultSet resultSetAfter;
        List<Long> after = new ArrayList<>();
        String sql2 = "SELECT COW_AFTER, SHEEP_AFTER, PIG_AFTER FROM HISTORY WHERE ID = (SELECT ID FROM (SELECT * FROM HISTORY ORDER BY ID DESC) WHERE ROWNUM <= 1) - 1";

        try {
            connection = connectionPool.getConnection();
            stmt = connection.createStatement();

            resultSetAfter = stmt.executeQuery(sql2);
            while (resultSetAfter.next()) {
                for (int i = 1; i <= 3; i++) {
                    after.add((long) resultSetAfter.getInt(i));
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return after;
    }

    public Long[] calculateSoldValues() {
        Long[] sold = new Long[3];
        List<Product> products = new ArrayList<Product>() {
            {
                add(Cow.INSTANCE);
                add(Sheep.INSTANCE);
                add(Pig.INSTANCE);
            }
        };
        List<Long> before = getLastBeforeValues();
        List<Long> after = getLastButOneAfterValues();

        if (after.size() < 1)
            return new Long[]{0L, 0L, 0L};

        for (int i = 0; i < 3; i++) {
            sold[i] = Math.abs(before.get(i) - after.get(i));
            Warehouse.INSTANCE.subtract(products.get(i), sold[i]);
        }

        return sold;
    }
}

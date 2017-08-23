package models;

import java.sql.*;


public class FlatModel {
    private static DbProperties dp = new DbProperties();

    private static Connection conn;

    private static PreparedStatement addFlatPS;
    private static PreparedStatement getCityIdPS;
    private static PreparedStatement deleteFlatPS;

    static{
        try {
            conn = DriverManager.getConnection(dp.getUrl(), dp.getUser(), dp.getPassword());

            addFlatPS = conn.prepareStatement("INSERT INTO Flat (" +
                    "Square," +
                    "RoomNumber," +
                    "Price," +
                    "City_id," +
                    "Street," +
                    "Building," +
                    "FlatNumber) VALUES(?, ?, ?, ?, ?, ?, ?)");

            getCityIdPS = conn.prepareStatement("SELECT id FROM city WHERE upper(city) = upper(?)");

            deleteFlatPS = conn.prepareStatement("DELETE FROM flat WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Integer getCityId(String city){
        Integer res = null;
        try {
            getCityIdPS.setString(1,city);

            ResultSet rs = getCityIdPS.executeQuery();
            if (rs.next()) {
                try {
                    res = rs.getInt(1);
                    return res;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    public static void addFlat(Flat flat) throws SQLException {
        try {
            addFlatPS.setInt(1, flat.getArea());
            addFlatPS.setByte(2, flat.getRoomNumber());
            addFlatPS.setDouble(3,flat.getPrice());
            addFlatPS.setInt(4, getCityId(flat.getCity()));
            addFlatPS.setString(5, flat.getStreet());
            addFlatPS.setString(6,flat.getBuilding());
            addFlatPS.setString(7,flat.getFlatNumber());

            int rc = addFlatPS.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            addFlatPS.close();
        }
    }
    public static void deleteFlat(int flatId) {
        try {
            deleteFlatPS.setInt(1, flatId);
            int rc = deleteFlatPS.executeUpdate(); // for INSERT, UPDATE & DELETE
            System.out.println("Deleted "+rc +" rows");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewFlats(String filterType, String par) throws SQLException {
        String baseSQL = "SELECT * FROM flat f,city c WHERE c.id = f.city_id ";
        String addCondition = getFilter(filterType,par);
        Statement vps = conn.createStatement();
        try {
            // table of data representing a database result set,
            System.out.println("SQL "+baseSQL+addCondition);
            ResultSet rs = vps.executeQuery(baseSQL+addCondition);
            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();
                int rowCount=0;
                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                    rowCount++;
                }
                if (rowCount == 0)
                    System.out.println("--------\nNo rows that satisfy filter condition\n-------------");
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            vps.close();
        }
    }

    private static void closeStatement(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatements(){
        closeStatement(addFlatPS);
        closeStatement(deleteFlatPS);
        closeStatement(getCityIdPS);
    }

    private static String getFilter(String filterType, String par){

        String condition = "";
        switch (filterType){
            case "1":
                condition = " AND f.id = "+par;
                break;
            case "2":
                condition = " AND price <= "+par;
                break;
            case "3":
                condition = " AND price >= "+par;
                break;
            case "4":
                condition = " AND city = '"+par+"'";
                break;
        }
        return condition;
    }
}

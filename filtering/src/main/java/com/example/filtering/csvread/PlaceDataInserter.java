package com.example.filtering.csvread;

import com.example.filtering.csvread.DatabaseUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlaceDataInserter {
    public static void insertPlaceData() {
        //삽입 query 구문
        String insertQuery = "INSERT INTO shop(company_name, shop_name, domain_name, phone_number, email, mail_order_number, business_type, first_report_date, address, shop_status,all_rating, information_rating, cooling_off_rating,payment_method_rating, terms_of_use_rating, security_rating, main_items, cooling_off_availability, initial_required_item, payment_method, degree_of_compliance_terms_of_use, handling_personal_information, requires_information_above_standard, buying_security_service, secure_server_installation, certification_mark, delivery_date_mark, cancel_delivery_charge_burden, board_operation, withdraw_method, website_open_year, monitoring_day) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseUtil.getConnection();
             Reader in = new FileReader("filtering/seoul_internet_shopping_mall_status.csv");
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            //Apache Commons CSV 라이브러리를 사용하여 CSV 파일을 파싱

            Long id = 0L;

            connection.setAutoCommit(false);
            try {
                //데이터 읽어오기
                for (CSVRecord record : records) {
                    id++;
                    preparedStatement.setLong(1, id);
                    preparedStatement.setString(2, record.get("company_name"));
                    preparedStatement.setString(3,record.get("shop_name"));
                    preparedStatement.setString(4,record.get("domain_name"));
                    preparedStatement.setString(5,record.get("phone_number"));
                    preparedStatement.setString(6,record.get("email"));
                    preparedStatement.setString(7, record.get("mail_order_number"));
                    preparedStatement.setString(8, record.get("business_type"));
                    preparedStatement.setString(9, record.get("first_report_date"));
                    preparedStatement.setString(10, record.get("address"));
                    preparedStatement.setString(11,record.get("shop_status"));
                    preparedStatement.setInt(12,Integer.getInteger(record.get("all_rating")));
                    preparedStatement.setInt(13,Integer.getInteger(record.get("information_rating")));
                    preparedStatement.setInt(14,Integer.getInteger(record.get("cooling_off_rating")));
                    preparedStatement.setInt(15,Integer.getInteger(record.get("payment_method_rating")));
                    preparedStatement.setInt(16,Integer.getInteger(record.get("terms_of_use_rating")));
                    preparedStatement.setInt(17,Integer.getInteger(record.get("security_rating")));
                    preparedStatement.setString(18,record.get("main_items"));
                    preparedStatement.setString(19,record.get("cooling_off_availability"));
                    preparedStatement.setString(20, record.get("initial_required_item"));
                    preparedStatement.setString(21, record.get("payment_method"));
                    preparedStatement.setString(22, record.get("degree_of_compliance_terms_of_use"));
                    preparedStatement.setString(23,record.get("handling_personal_information"));
                    preparedStatement.setString(24, record.get("requires_information_above_standard"));
                    preparedStatement.setString(25, record.get("buying_security_service"));
                    preparedStatement.setString(26, record.get("secure_server_installation"));
                    preparedStatement.setString(27, record.get("certification_mark"));
                    preparedStatement.setString(28, record.get("delivery_date_mark"));
                    preparedStatement.setString(29, record.get("cancel_delivery_charge_burden"));
                    preparedStatement.setString(30, record.get("board_operation"));
                    preparedStatement.setString(31, record.get("withdraw_method"));
                    preparedStatement.setString(32, record.get("website_open_year"));
                    preparedStatement.setString(33, record.get("monitoring_day"));

                    //현재 설정된 값들을 배치에 추가.
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();

                //트랜잭션을 커밋하여 데이터베이스에 변경사항을 반영
                connection.commit();
            } catch (Exception e) {
                connection.rollback(); //트랜잭션을 롤백하여 변경사항을 취소
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package publicDustProfect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class FunctionsDustInfo 
{
	public static Scanner input = new Scanner(System.in);
	public static void insertDustInfo(ArrayList<DustInfo> dustInfoList) 
	{
		 if(dustInfoList.size() <1) {
	            System.out.println("입력할 데이터가 없어요!");
	            return;
	        }

	        Connection con = null; 
	        PreparedStatement pstmt = null; 
	        try {
	            con = DBUtil.getConnection();
	            for(DustInfo data  : dustInfoList ) {
	                String sql = "insert into dustinfo values(?,?,?,?,?,?,?,?)";
	                pstmt = con.prepareStatement(sql);
	                pstmt.setInt(1,data.getClearVal());
	                pstmt.setInt(2,data.getSn());
	                pstmt.setString(3,data.getDistrictName());
	                pstmt.setDate(4,data.getDataDate());
	                pstmt.setInt(5,data.getIssueVal());
	                pstmt.setString(6,data.getMoveName());
	                pstmt.setString(7,data.getIssueGbn());
	                pstmt.setString(8,data.getItemCode());
	                int value = pstmt.executeUpdate();

	                if(value == 1) {
	                    System.out.println(data.getDistrictName()+"지역 등록완료");
	                }else {
	                    System.out.println(data.getDistrictName()+"지역 등록실패");
	                }
	            }//end of for
	        } catch (SQLException e) {
	            e.printStackTrace();
			} finally {
	                try {
	                    if(pstmt != null) {
	                        pstmt.close();
	                    }
	                    if(con != null) {
	                        con.close();
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	        }
		
	}

	public static void deleteDustInfo() 
	{
        int count = getCountDustInfo();
        if(count == 0) {
            System.out.println("지역 정보가 없습니다.");
            return; 
        }
        String sql = "delete from dustinfo";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            int value = pstmt.executeUpdate();
            if(value != 0) {
                System.out.println("모든 지역정보 삭제완료");
            }else {
                System.out.println("모든 지역정보 삭제실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
	}
	public static int getCountDustInfo() {
		int count = 0; 
        String sql = "select count(*) as cnt from dustinfo";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt("cnt");
                System.out.println("count="+count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return count; 
	}
	public static void updateDustInfo(int data) 
	{
		String sql = "UPDATE dustinfo SET datadate = SYSDATE WHERE sn = ?";
	        Connection con = null; 
	        PreparedStatement pstmt = null; 
	        try {
	            con = DBUtil.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, data);
	            int value = pstmt.executeUpdate();

	            if(value == 1) 
	            {
	                System.out.println(data+" 수정완료");
	            }
	            else 
	            {
	                System.out.println(data+" 수정실패");
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	                try {
	                    if(pstmt != null) {
	                        pstmt.close();
	                    }
	                    if(con != null) {
	                        con.close();
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	        }
		
	}
	public static int updateDustNodeno() {
		ArrayList<DustInfo> dustInfoList = selectDustInfo();
        printDustInfo(dustInfoList);
        System.out.println("update sn(관리번호) >> ");
        int data = Integer.parseInt(input.nextLine());
        return data;
	}
	public static void printDustInfo(ArrayList<DustInfo> dustInfoSelectList) {
		        if(dustInfoSelectList.size() < 1) {
		            System.out.println("출력할 미세먼지 정보가 없습니다.");
		            return;
		        }
		        for(DustInfo data  : dustInfoSelectList) {
		            System.out.println(data.toString());
		        }

		
	}
	public static ArrayList<DustInfo> selectDustInfo() {
		ArrayList<DustInfo> dustInfoList = null;
        String sql = "select * from dustinfo";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            dustInfoList = new ArrayList<DustInfo>(); 
            while(rs.next()) {
            	DustInfo bif = new DustInfo();
                bif.setClearVal(rs.getInt("CLEARVAL"));
                bif.setSn(rs.getInt("SN"));
                bif.setDistrictName(rs.getString("DISTRICTNAME"));
                bif.setDataDate(rs.getDate("DATADATE"));
                bif.setIssueVal(rs.getInt("ISSUEVAL"));
                bif.setMoveName(rs.getString("MOVENAME"));
                bif.setIssueGbn(rs.getString("ISSUEGBN"));
                bif.setItemCode(rs.getString("ITEMCODE"));
                dustInfoList.add(bif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return dustInfoList; 
	}
	

	
}

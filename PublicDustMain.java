package publicDustProfect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PublicDustMain 
{
	public static Scanner input = new Scanner (System.in);
	public static void main(String[] args) 
	{
		ArrayList<DustInfo> DustInfoList = null;
		ArrayList<DustInfo> DustInfoSelectList= new ArrayList<DustInfo>();
		boolean exitFlag = false;
		while(!exitFlag) 
		{
			System.out.println("1.웹정보가져오기, 2.저장하기 3.테이블읽어오기, 4.수정하기 5.삭제하기, 6.종료");
			System.out.println("선택>>");
			int count =Integer.parseInt(input.nextLine());
			switch(count) 
			{
			case 1:DustInfoList = WebConnention.webConnention();
				break;
			case 2:if(DustInfoList.size() < 1) 
			{
				System.out.println("공공데이터로부터 가져온 자료가 없습니다");
				continue;
			}
			FunctionsDustInfo.insertDustInfo(DustInfoList);
				break;
			case 3:DustInfoSelectList = FunctionsDustInfo.selectDustInfo();
			FunctionsDustInfo.printDustInfo(DustInfoSelectList);
				break;
			case 4:     
				int data = 	FunctionsDustInfo.updateDustNodeno();
            if(data != 0) 
            {
            	FunctionsDustInfo.updateDustInfo(data);
            }
            break;
			
			case 5:	FunctionsDustInfo.deleteDustInfo();
				break;
			case 6:
				exitFlag = true;
				break;
			}
		}
}
	
	}

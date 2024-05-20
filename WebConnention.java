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
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WebConnention 
{
	public static  ArrayList<DustInfo> webConnention()
	{
		// 1. 요청 url 생성
		ArrayList<DustInfo> list = new ArrayList<>();
		
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
		try {
			urlBuilder.append("?" + URLEncoder.encode("year", "UTF-8")+ "=" + URLEncoder.encode("2020", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=r%2Fku8cm4c1roRvDcn1Wn7BOZ6VTooJmtzlXZhB4A69D9GPNY3fEMB2w2cnXm175kdagn1L7J5SOo2tEODmSPVQ%3D%3D");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//2.서버주소 Connection con
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlBuilder.toString()); 				//웹서버주소 action 
			conn = (HttpURLConnection) url.openConnection();	// 접속요청 get방식
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//3. 요청내용을 전송 및 응답처리
		BufferedReader br = null;
		try {
			//conn.getResponseCode() 서버에서 상태코드를 알려주는 값
			int statusCode = conn.getResponseCode();
			System.out.println(statusCode);
			if (statusCode >= 200 && statusCode <= 300) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			Document doc = parseXML(conn.getInputStream());
			// a. field 태그객체 목록으로 가져온다.
			NodeList descNodes = doc.getElementsByTagName("item");
			// b. Corona19Data List객체 생성
			// c. 각 item 태그의 자식태그에서 정보 가져오기
			for (int i = 0; i < descNodes.getLength(); i++) {
				// item
				Node item = descNodes.item(i);
				DustInfo dustInfo = new DustInfo();
				// item 자식태그에 순차적으로 접근
				for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
					//System.out.println(node.getNodeName() + " : " + node.getTextContent());
					
					switch (node.getNodeName()) {
					case "clearVal":
						dustInfo.setClearVal(Integer.parseInt(node.getTextContent())); 
						break;
					case "sn":
						dustInfo.setSn(Integer.parseInt(node.getTextContent()));
						break;
					case "districtName":
						dustInfo.setDistrictName(node.getTextContent());
						break;
					case "issueVal":
						dustInfo.setIssueVal(Integer.parseInt(node.getTextContent()));
						break;
					case "moveName":
						dustInfo.setMoveName(node.getTextContent());
						break;
					case "issueGbn":
						dustInfo.setIssueGbn(node.getTextContent());
						break;
					case "itemCode":
						dustInfo.setItemCode(node.getTextContent());
						break;
					}
				}
				// d. List객체에 추가
				list.add(dustInfo);
			}
			// e.최종확인
			for (DustInfo data : list) {
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static Document parseXML(InputStream inputStream) {
		DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(inputStream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) { // Simple API for XML e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}

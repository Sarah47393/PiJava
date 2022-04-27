/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.MyDb;


public class Sauvegarde {
public void imprimer(){
try{
String file_name="C:\\evenement2.pdf";
Document document=new Document();
PdfWriter.getInstance(document, new FileOutputStream(file_name));
document.open();
MyDb obJDBConnection=new MyDb();
Connection connection=obJDBConnection.getCnx();
PreparedStatement ps=null;
ResultSet rs=null;

String query="select * from evenement";
ps=connection.prepareStatement(query);
rs=ps.executeQuery();
Paragraph para=new Paragraph("Welcome To Visual Arts\n"
 + "Liste Des Evenement\n");
document.add(para);
while(rs.next()){
Paragraph parag=new Paragraph(rs.getString("nom_evenement")+rs.getString("description")+" "+rs.getInt("billet")+" "+rs.getString("date_de_evenement"));
document.add(parag);
document.add(new Paragraph(" "));

}

//document.add(Image.getInstance("C:\reloua.png"));
//document.close();
System.out.println("PDF");
}catch (Exception e){
System.err.println(e);
}
}
}
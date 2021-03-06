import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXB extends DefaultHandler {

	HashMap<String, String> infos = new HashMap<String,String>();
	boolean estTrack;
	boolean estAuthor;
	boolean estTitre;
	String titre;
	String author;
	String track;
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(estTitre) {
			this.titre = new String(ch,start,length);
		} else if(estAuthor) {
			this.author = new String(ch,start,length);
		} else if (estTrack) {
			this.track = new String(ch,start,length);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("track_ID")){
			this.estTrack = false;
			if(this.infos.containsKey(track)) {
				System.out.println(this.infos.get(track));
			}
		} else if (qName.equals("artist")) {
			this.estAuthor = false;
		} else if (qName.equals("name")) {
			this.estTitre = false;
		} else if (qName.equals("song")) {
			this.infos.put(this.track, (this.author + " - " + this.titre));
		} else if (qName.equals("list")) {
			System.out.println("---------------------------------");
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if(qName.equals("track_ID")) {
			this.estTrack = true;
		} else if(qName.equals("artist")) {
			this.estAuthor = true;
		} else if(qName.equals("name")) {
			this.estTitre = true;
		} else if (qName.equals("list")) {
			System.out.println(atts.getValue("name"));
		}
	}
	
}

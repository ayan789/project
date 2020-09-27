package com.ccic.salesapp.noncar.dto;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccic.salessapp.common.outService.ws.common.bean.ResponseDTOCommAttr;


/**
 * <p>
 * Java class for CarApplicationSearchResponseDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;CarApplicationSearchResponseDTO&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://service.ccic.com/mss/common/bean}ResponseDTOCommAttr&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;applications&quot; type=&quot;{http://service.ccic.com/mss/carapplication/bean}CarApplicationDTO&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarApplicationSearchResponseDTO", propOrder = { "applications" })
public class CarApplicationSearchResponseDTO extends ResponseDTOCommAttr {

	@XmlElement(nillable = true)
	protected List<CarApplicationDTO> applications;

	/**
	 * Gets the value of the applications property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the applications property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getApplications().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CarApplicationDTO }
	 * 
	 * 
	 */
	public List<CarApplicationDTO> getApplications() {
		if (applications == null) {
			applications = new ArrayList<CarApplicationDTO>();
		}
		return this.applications;
	}

}

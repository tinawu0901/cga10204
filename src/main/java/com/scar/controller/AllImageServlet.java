package com.scar.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventInformationService;
import com.event.model.EventInformationVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.scar.model.ScarService;
import com.scar.model.ScarVO;

@WebServlet("/allImages")
public class AllImageServlet extends HttpServlet {
	EventInformationService eventService = new EventInformationService();
	MemberService memberservice = new MemberService();
	ScarService scarservice = new ScarService();
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		byte[] imageData = null;
		String event_no = req.getParameter("event_no");
		if(event_no !=null && event_no.trim().length() !=0) {
			EventInformationVO isExist= eventService.findByOne(Integer.valueOf(event_no));
			if(isExist.getEvent_photo()!=null) {
			 imageData = isExist.getEvent_photo();
			}
		}
		
		String meb_no = req.getParameter("meb_no");
		if(meb_no !=null && meb_no.trim().length() !=0) {
			MemberVO m = memberservice.member(meb_no);
			if(m.getMeb_profile() != null)
				imageData = m.getMeb_profile();
		}
		String scar_no = req.getParameter("scar_no");
		if(scar_no !=null && scar_no.trim().length() !=0) {
			ScarVO sc = scarservice.getScar(scar_no);
			if(sc.getScar_photo() !=null)
				imageData = sc.getScar_photo();
		}
		
		res.setContentType("image/jpeg");
		res.getOutputStream().write(imageData);
		res.getOutputStream().flush();
		res.getOutputStream().close();
	}
	
}

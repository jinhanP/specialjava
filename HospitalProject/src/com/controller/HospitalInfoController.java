package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.vo.HospitalVO;

public class HospitalInfoController {
	private List<HospitalVO> list = new ArrayList();

	public void inputData() {
		File data = new File("C:/download/hospitaldata.csv");
		StringBuffer sb = new StringBuffer();
		FileReader fr = null;
		try {
			fr = new FileReader(data);
			BufferedReader breader = new BufferedReader(fr);
			String ch = null;
			while ((ch = breader.readLine()) != null) {
				sb.append(ch + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		String[] datas = sb.toString().split("\n");
		for (String d : datas) {
			String[] detail = d.split(",");
			int count = 0;
			try {
				count = Integer.parseInt(detail[16]);
				list.add(new HospitalVO(detail[3], detail[1], detail[5], detail[7], detail[10], detail[11], detail[12],
						detail[13], count));
			} catch (NumberFormatException e) {
//System.out.printf("error detail[16] = %s \n", detail[16]);
//e.printStackTrace();
			}
		}
//항목명 삭제
		list.remove(0);
	}

	public void printHospitalData() {
		System.out.println("종류\t" + "병원명\t\t\t\t" + "시도명\t\t" + "시군구명\t\t" + "주소\t\t\t\t\t" + "전화번호\t\t" + "홈페이지주소\t\t"
				+ "개설일\t" + "총의사수");
		for (HospitalVO h : list) {
			if (h.getCategory().equals("종합병원")) {
				System.out.printf("%-5s %-30s %-10s %-10s %-30s %-10s %-40s %-20s %-4d\n", h.getCategory(),
						h.getHospitalName(), h.getStateCode(), h.getCityCode(), h.getAddress(), h.getPhone(),
						h.getSite(), h.getOpenDate(), h.getDoctorCount());
			}
		}
	}
}
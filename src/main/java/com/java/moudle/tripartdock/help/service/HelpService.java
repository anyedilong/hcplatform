package com.java.moudle.tripartdock.help.service;

import java.util.List;
import java.util.Map;

import com.java.moudle.tripartdock.help.admin.BltCommonProblem;

public interface HelpService {

	Map<String, List<BltCommonProblem>> getCommonProblem();

}

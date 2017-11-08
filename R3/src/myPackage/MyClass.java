package myPackage;

import java.util.ArrayList;

import p.actions.*;

public class MyClass {
	public void myMethod() {
		///////////////////////////////////////////////////////////////////////
		//
		//	Write your script here
		// Create a Visitor Pattern
		  RPackage pkg = RProject.getPackage("RefactoringCrawler", "edu.uiuc.detectRefactorings.detection");
		  RClassOrInterface vis = pkg.newClass("LikelinessVisitor");
		  RField instance = vis.addSingleton();
		  RClassOrInterface fd =  pkg.getClass("FieldDetection");
		  String[] sig = {"computeLikeliness", "edu.uiuc.detectRefactorings.util.Node", "edu.uiuc.detectRefactorings.util.Node"};
		  RMethod comp = fd.getMethod(sig);
		  ArrayList<RMethod> relatives = comp.getRelatives();
		  for(RMethod rel : relatives)
		  {
			  rel.addParameter(vis, "LikelinessVisitor.instance");
		  }
		  for(RMethod rel : relatives)
		  {
			  RParameter del = rel.getParameter(2);
			  rel.moveAndDelegate(del);
		  }
		  String[] sig2 = {"computeLikeliness", "edu.uiuc.detectRefactorings.util.Node", "edu.uiuc.detectRefactorings.util.Node","edu.uiuc.detectRefactorings.detection.LikelinessVisitor" };
		  comp = fd.getMethod(sig2);
		  relatives = comp.getRelatives();
		  for(RMethod rel : relatives)
		  {
			  rel.rename("accept");
		  }
		  ArrayList<RMethod> visitingMethods = vis.getAllMethods();
		  for(RMethod visitM : visitingMethods)
		  {
			  visitM.rename("visit");
		  }
		//
		///////////////////////////////////////////////////////////////////////
	}
}

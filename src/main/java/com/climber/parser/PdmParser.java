package com.climber.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PdmParser {
	private Element root;

	public List<Domain> loadAllDomains() {
		List<Domain> allDomains = new ArrayList<Domain>();
		Element domainNode = (Element) root.selectSingleNode("//c:Domains");
		if (domainNode != null) {
			for (Element e : domainNode.elements()) {
				Domain domain = new Domain();
				String domainId = e.attributeValue("Id");
				String domainName = e.elementText("Name");
				domain.setDomainId(domainId);
				domain.setDomainName(domainName);
				allDomains.add(domain);
			}
		}

		return allDomains;
	}

	public List<Reference> loadAllReferences() {
		List<Reference> allReferences = new ArrayList<Reference>();
		Element refNode = (Element) root.selectSingleNode("//c:References");
		if (refNode != null) {
			for (Element e : refNode.elements()) {
				Reference ref = new Reference();
				Element parenttable = e.element("ParentTable");
				if (parenttable != null) {
					Element table = parenttable.element("Table");
					if (table != null) {
						String parentId = table.attributeValue("Ref");
						ref.setParentId(parentId);
					}
				}

				Element joins = e.element("Joins");
				if (joins != null) {
					Element referencejoin = joins.element("ReferenceJoin");
					if (referencejoin != null) {
						Element object2 = referencejoin.element("Object2");
						if (object2 != null) {
							Element col = object2.element("Column");
							if (col != null) {
								String FKId = col.attributeValue("Ref");
								ref.setFKId(FKId);
							}
						}
					}
				}

				allReferences.add(ref);
			}
		}
		return allReferences;
	}

	/**
	 * 加载所有的表
	 * 
	 * @param args
	 */
	public List<Table> loadAllTables() {
		List<Table> allTables = new ArrayList<Table>();
		// 主键
		List<String> allPKIds = loadAllPKIds();
		// 外键
		List<Reference> allReferences = loadAllReferences();

		Element tableNode = (Element) root.selectSingleNode("//c:Tables");
		for (Element t : tableNode.elements()) {
			Table table = new Table();
			List<Column> allColumns = new ArrayList<Column>();
			String tableId = t.attributeValue("Id");
			String tableName = t.elementText("Name");
			String tableCode = t.elementText("Code");

			// 1
			table.setTableId(tableId);
			// 2
			table.setTableName(tableName);
			// 3
			table.setTableCode(tableCode);
			// Column信息添加
			Element columnNode = t.element("Columns");
			for (Element col : columnNode.elements()) {
				Column column = new Column();
				String columnId = col.attributeValue("Id");
				String columnName = col.elementText("Name");
				String columnCode = col.elementText("Code");
				String columnComment = col.elementText("Comment");
				String dataType = col.elementText("DataType");

				boolean PK = false;
				// 获取主键
				if (allPKIds.contains(columnId)) {
					PK = true;
				}
				boolean FK = false;
				// 获取外键
				for (Reference ref : allReferences) {
					if (columnId.equals(ref.getFKId())) {
						FK = true;
						// 4
						table.setParentTableId(ref.getParentId());
					}
				}
				column.setColId(columnId);
				column.setColName(columnName);
				column.setColCode(columnCode);
				column.setColComment(columnComment);
				column.setDataType(dataType);
				column.setPK(PK);
				column.setFK(FK);
				allColumns.add(column);
			}
			// 5
			table.setAllColumns(allColumns);
			allTables.add(table);
		}
		return allTables;
	}

	public List<String> loadAllPKIds() {
		List<String> allPKIds = new ArrayList<String>();
		Element tableNode = (Element) root.selectSingleNode("//c:Tables");
		for (Element e : tableNode.elements()) {
			// 判断是否有主键
			Element keys = e.element("Keys");
			if (keys != null) {
				Element key = keys.element("Key");
				if (key != null) {
					Element keycol = key.element("Key.Columns");
					if (keycol != null) {
						Element col = keycol.element("Column");
						if (col != null) {
							String PKId = col.attributeValue("Ref");
							allPKIds.add(PKId);
						}
					}
				}
			}
		}
		return allPKIds;
	}

	public Map<String, String> loadTableCodeLib() {
		Map<String, String> tableCodeLib = new HashMap<String, String>();
		Element tableNode = (Element) root.selectSingleNode("//c:Tables");
		for (Element t : tableNode.elements()) {
			String id = t.attributeValue("Id");
			String name = t.elementText("Name");
			String code = t.elementText("Code");
			tableCodeLib.put(id, name + code);
		}
		return tableCodeLib;
	}

	public void printAllTables(List<Table> allTables) {
		System.out.println("总共有：" + allTables.size() + "张表");
		for (Table table : allTables) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("表:" + table.getTableName() + table.getTableCode());
			String tablePK = "";
			String tableFK = "";
			for (Column column : table.getAllColumns()) {
				System.out.println("字段:" + column.getColName() + column.getColCode() + "," + "注释:" + column.getColComment() + "," + "数据类型:" + column.getDataType());
				if (column.isPK())
					tablePK += column.getColName() + column.getColCode();
				if (column.isFK())
					tableFK += column.getColName() + column.getColCode();
			}
			System.out.println("主键:" + tablePK + "外键:" + tableFK);
		}
	}

	public PdmParser() {
		try {
			SAXReader reader = new SAXReader();
			File pdm = new File("F:/1.pdm");
			if (pdm.exists()) {
				Document doc = reader.read(pdm);
				root = doc.getRootElement();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PdmParser pp = new PdmParser();
		pp.printAllTables(pp.loadAllTables());
	}
}

package io.github.Dinner1111.ServerUtils.ProjectBuilder;

import io.github.Dinner1111.ServerUtils.Misc.SharedVariables;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import nu.xom.*;

public class ScriptStorage {
	Document XML;
	Scripts scripts;
	Element root = new Element("scripts");
	Element commands = new Element("commands");
	Element commandSender = new Element("commandsender");
	SharedVariables sv;
	
	public ScriptStorage(SharedVariables s) {
		sv = s;
	}
	public void runScript(String script) throws Exception {
		try {
			for (String command : convertNodesToCommands(script).split(",")) {
				Bukkit.dispatchCommand(getScriptSender(script), command);
			}
		} catch (Exception e) {
			throw new Exception("Could not run script \'" + script + "\'!");
		}
	}
	protected String convertNodesToCommands(String script) {
		String commands = "";
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute(0).toString().equals(script)) {
				Elements insideScript = elements.get(i).getChildElements();
				for (int inside = 0; inside < insideScript.size(); inside++) {
					if (insideScript.get(inside).toString().equals("commands")) {
						Elements insideCommands = elements.get(inside).getChildElements();
						for (int totalCommands = 0; totalCommands < insideCommands.size(); totalCommands++) {
							commands += insideCommands.get(totalCommands).getChild(0).toString() + ",";
						}
					}
				}
			}
		}
		return commands;
	}
	public void createScript(String name) {
		Element script = new Element("script");
		Attribute scriptName = new Attribute("name", name);
		script.addAttribute(scriptName);
		script.appendChild(commands);
		script.appendChild(commandSender);
	}
	public void addScriptCommand(String command, String script) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute(0).toString().equals(script)) {
				Elements insideScript = elements.get(i).getChildElements();
				for (int inside = 0; inside < insideScript.size(); inside++) {
					if (insideScript.get(inside).toString().equals("commands")) {
						Element scriptCommand = new Element("command");
						scriptCommand.appendChild(command);
						commands.appendChild(scriptCommand);
						return;
					}
				}
			}
		}
	}
	public void setScriptSender(String sender, String script) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute(0).toString().equals(script)) {
				Elements insideScript = elements.get(i).getChildElements();
				for (int inside = 0; inside < insideScript.size(); inside++) {
					if (insideScript.get(inside).toString().equals("commandsender")) {
						Element scriptSender = new Element("sender");
						scriptSender.appendChild(sender);
						commandSender.appendChild(scriptSender);
						return;
					}
				}
			}
		}
	}
	public CommandSender getScriptSender(String script) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute(0).toString().equals(script)) {
				Elements insideScript = elements.get(i).getChildElements();
				for (int inside = 0; inside < insideScript.size(); inside++) {
					if (insideScript.get(inside).toString().equals("commandsender")) {
						if (insideScript.get(inside).getChild(0).toString().equals("player")) {
							return sv.getSender();
						} else {
							return Bukkit.getConsoleSender();
						}
					}
				}
			}
		}
		return null;
	}
	public void saveScript() {
		root.appendChild(commands);
		root.appendChild(commandSender);
		Elements oldXML = XML.getRootElement().getChildElements();
		for (int i = 0; i > oldXML.size(); i++)
			root.appendChild(oldXML.get(i));
		XML = new Document(root);
	}
	public boolean deleteScript(String name) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute(0).toString().equals(name)) {
				elements.get(i).removeChild(i);
				return true;
			}
		}
		return false;
	}
	public boolean deleteCommand(String script, String command) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute(0).toString().equals(script)) {
				Elements insideScript = elements.get(i).getChildElements();
				for (int inside = 0; inside < insideScript.size(); inside++) {
					if (insideScript.get(inside).toString().equals("commands")) {
						Elements commands = elements.get(inside).getChildElements();
						for (int totalCommands = 0; totalCommands < commands.size(); totalCommands++) {
							if (commands.get(totalCommands).getChild(totalCommands).toString().equals(command)) {
								insideScript.get(inside).removeChild(totalCommands);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	public boolean checkScriptName(String name) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++)
			if (elements.get(i).getAttribute(0).toString().equals(name)) return false;
		return true;
	}
	public boolean checkScriptSender(String senderName) {
		if (senderName.equalsIgnoreCase("player")) return true;
		if (senderName.equalsIgnoreCase("console")) return true;
		return false;
	}
	public boolean checkScript(String script) {
		Elements elements = XML.getRootElement().getChildElements();
		for (int i = 0; i < elements.size(); i++)
			if (elements.get(i).getAttribute(0).toString().equals(script)) return true;
		return false;
	}
}
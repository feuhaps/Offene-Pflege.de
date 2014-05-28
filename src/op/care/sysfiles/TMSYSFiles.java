/*
 * OffenePflege
 * Copyright (C) 2011 Torsten Löhr
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License V2 as published by the Free Software Foundation
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if not, write to
 * the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * www.offene-pflege.de
 * ------------------------
 * Auf deutsch (freie Übersetzung. Rechtlich gilt die englische Version)
 * Dieses Programm ist freie Software. Sie können es unter den Bedingungen der GNU General Public License,
 * wie von der Free Software Foundation veröffentlicht, weitergeben und/oder modifizieren, gemäß Version 2 der Lizenz.
 *
 * Die Veröffentlichung dieses Programms erfolgt in der Hoffnung, daß es Ihnen von Nutzen sein wird, aber
 * OHNE IRGENDEINE GARANTIE, sogar ohne die implizite Garantie der MARKTREIFE oder der VERWENDBARKEIT FÜR EINEN
 * BESTIMMTEN ZWECK. Details finden Sie in der GNU General Public License.
 *
 * Sie sollten ein Exemplar der GNU General Public License zusammen mit diesem Programm erhalten haben. Falls nicht,
 * schreiben Sie an die Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA.
 */
package op.care.sysfiles;

import entity.files.SYSFiles;
import entity.files.SYSFilesTools;
import op.OPDE;
import op.tools.SYSConst;
import op.tools.SYSTools;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author tloehr
 */
public class TMSYSFiles extends AbstractTableModel {
    public static final int COL_PIT = 0;
    public static final int COL_FILE = 1;
    public static final int COL_DESCRIPTION = 2;

    ArrayList<SYSFiles> mymodel;

    public TMSYSFiles(ArrayList<SYSFiles> modelData) {
        mymodel = modelData;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }
    public void setSYSFile(int row, SYSFiles sysfile){
        mymodel.set(row, sysfile);
        fireTableRowsUpdated(row, row);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public SYSFiles getRow(int row) {
        return mymodel.get(row);
    }

    @Override
    public int getRowCount() {
        int rowcount = 0;

        if (mymodel != null) {
            rowcount = mymodel.size();
        }
        return rowcount;
    }

    private String getAttachmentsAsHTML(int row) {
        SYSFiles sysfile = mymodel.get(row);
        String result = "";

        result += sysfile.getNrAssignCollection().isEmpty() ? "" : OPDE.lang.getString("nursingrecords.reports") + " " + sysfile.getNrAssignCollection().size() + ", ";
        result += sysfile.getBwiAssignCollection().isEmpty() ? "" : OPDE.lang.getString("nursingrecords.info") + " " + sysfile.getBwiAssignCollection().size() + ", ";
        result += sysfile.getPreAssignCollection().isEmpty() ? "" : OPDE.lang.getString("nursingrecords.prescription") + " " + sysfile.getPreAssignCollection().size() + ", ";

        String html = OPDE.lang.getString(PnlFiles.internalClassID+".Attachments")+": ";
        if (result.isEmpty()) {
            html += html = OPDE.lang.getString("misc.msg.none");
        } else {
            html += result; //result.substring(0, result.length() - 3);
        }

        return html;
    }

    @Override
    public Object getValueAt(int row, int column) {
        String value = "";
        switch (column) {
            case 0: {
                value += SYSFilesTools.getDatumUndUser(mymodel.get(row));
                break;
            }
            case 1: {
                value += SYSConst.html_fontface;
                value += mymodel.get(row).getFilename() + ", ";
                value += OPDE.lang.getString("misc.msg.Size") + ": " + BigDecimal.valueOf(mymodel.get(row).getFilesize()).divide(new BigDecimal(1048576), 2, BigDecimal.ROUND_HALF_UP) + " mb";
//                value += ", " + getAttachmentsAsHTML(row);
                value += "</font>";
                break;
            }
            case 2: {
                value += SYSConst.html_fontface;
                value += "<p>"+SYSTools.catchNull(mymodel.get(row).getBeschreibung())+"</p>";
                value += "</font>";
                break;
            }
            default: {
                value = null;
            }
        }
        return value;
    }
}

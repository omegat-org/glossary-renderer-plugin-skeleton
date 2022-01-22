/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2019 Thomas Cordonnier
               2022 Hiroshi Miura
               Home page: http://www.omegat.org/
               Support center: https://omegat.org/support

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.gui.glossary;

import org.omegat.util.gui.TooltipAttribute;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Custom glossary sample renderer.
 * @author Hiroshi Miura
 */
public class CustomGlossaryRenderer implements IGlossaryRenderer {

    /**
     * Plugin loader.
     */
    public static void loadPlugins() {
        GlossaryRenderers.addGlossaryRenderer(new CustomGlossaryRenderer());
    }

    /**
     * Plugin unloader.
     */
    public static void unloadPlugins() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Custom renderer";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return getClass().getCanonicalName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final GlossaryEntry entry, final IRenderTarget<?> trg) {
        trg.append(entry.getSrcText(), SOURCE_ATTRIBUTES);
        trg.append("\u2192 ");

        String[] targets = entry.getLocTerms(false);
        String[] comments = entry.getComments();
        boolean[] priorities = entry.getPriorities();
        String[] origins = entry.getOrigins(false);

        for (int i = 0; i < targets.length; i++) {
            if (i == 0 || (!targets[i].equals(targets[i - 1]))) {
                SimpleAttributeSet attrs = new SimpleAttributeSet(TARGET_ATTRIBUTES);
                if (priorities[i]) {
                    StyleConstants.setBold(attrs, true);
                }
                attrs.addAttribute(TooltipAttribute.ATTRIBUTE_KEY, new TooltipAttribute(origins[i]));
                trg.append(targets[i], attrs);
                if (i < targets.length - 1) {
                    trg.append(" / ", null);
                }
            }
            if (!comments[i].equals("")) {
                trg.append("(" + comments[i] + ")", NOTES_ATTRIBUTES);
            }
        }
    }
}

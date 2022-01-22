/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2022 Hiroshi Miura
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

package org.omegat.gui.glossary

import org.omegat.util.gui.TooltipAttribute

import javax.swing.text.SimpleAttributeSet

import static javax.swing.text.StyleConstants.setBold;

/**
 * Custom glossary sample renderer.
 * @author Hiroshi Miura
 */
class CustomGlossaryRenderer implements IGlossaryRenderer {

    static void loadPlugins() {
        GlossaryRenderers.addGlossaryRenderer(new CustomGlossaryRenderer())
    }

    static void unloadPlugins() {
    }

    String getName() {
        "Custom renderer"
    }

    String getId() {
        getClass().canonicalName
    }

    void render(final GlossaryEntry entry, final IRenderTarget<?> trg) {
        def terms = new GlossaryEntryIterator(entry)
        trg.append entry.getSrcText(), SOURCE_ATTRIBUTES
        trg.append "\u2192 "
        terms.each { GlossaryTerm t ->
            def attrs = new SimpleAttributeSet(TARGET_ATTRIBUTES)
            if (t.priority) {
                setBold attrs, true
            }
            attrs.addAttribute TooltipAttribute.ATTRIBUTE_KEY, new TooltipAttribute(t.origin)
            trg.append t.target, attrs
            if (!t.comment.equals("")) {
                trg.append "(" + t.comment + ")", NOTES_ATTRIBUTES
            }
            trg.append "\n", null
        }
    }
}

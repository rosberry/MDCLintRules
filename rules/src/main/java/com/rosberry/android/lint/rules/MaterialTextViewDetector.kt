package com.rosberry.android.lint.rules

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Element
import org.w3c.dom.Node

/**
 * @author Alexei Korshun on 11.09.2020.
 */
class MaterialTextViewDetector : ResourceXmlDetector(), SourceCodeScanner {

    override fun appliesTo(folderType: ResourceFolderType): Boolean = folderType == ResourceFolderType.LAYOUT

    override fun getApplicableElements(): Collection<String>? =
            listOf("androidx.appcompat.widget.AppCompatTextView", "TextView")

    override fun visitElement(context: XmlContext, element: Element) {
        super.visitElement(context, element)
        reportUsage(context, element)
    }

    private fun reportUsage(context: XmlContext, node: Node) {
        val fix = fix()
            .name("Replace")
            .replace()
            .text(node.nodeName)
            .with("com.google.android.material.textview.MaterialTextView")
            .autoFix()
            .build()

        context.report(
                issue = ISSUE,
                scope = node,
                location = context.getLocation(node, 1, node.nodeName.length + 1),
                message = "Use MDC instead of androidx or android widgets.",
                quickfixData = fix
        )
    }

    companion object {

        private val IMPLEMENTATION = Implementation(MaterialTextViewDetector::class.java, Scope.RESOURCE_FILE_SCOPE)

        val ISSUE: Issue = Issue
            .create(
                    id = "MaterialComponentDetector",
                    briefDescription = "Use MDC Component",
                    explanation = "You should use MaterialTextView".trimIndent(),
                    category = Category.USABILITY,
                    priority = 4,
                    severity = Severity.WARNING,
                    androidSpecific = true,
                    implementation = IMPLEMENTATION
            )
    }
}
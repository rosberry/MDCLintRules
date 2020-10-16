package com.rosberry.android.lint.rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

/**
 * @author Alexei Korshun on 11.09.2020.
 */
class MyIssueRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(MaterialTextViewDetector.ISSUE, MaterialButtonDetector.ISSUE, MaterialSwitchDetector.ISSUE,
                MaterialToolbarDetector.ISSUE, MaterialSliderDetector.ISSUE, MaterialCardViewDetector.ISSUE,
                MaterialCheckBoxDetector.ISSUE)
}
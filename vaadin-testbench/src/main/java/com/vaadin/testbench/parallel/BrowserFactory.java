/**
 * Copyright (C) 2012 Vaadin Ltd
 *
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the license along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 */
package com.vaadin.testbench.parallel;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {

    public DesiredCapabilities create(Browser browser) {
        return create(browser, "", Platform.ANY);
    }

    public DesiredCapabilities create(Browser browser, String version) {
        return create(browser, version, Platform.ANY);
    }

    public DesiredCapabilities create(Browser browser, String version,
            Platform platform) {
        DesiredCapabilities desiredCapabilities;

        switch (browser) {
        case FIREFOX:
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setVersion(version);
            desiredCapabilities.setPlatform(platform);
            break;
        case CHROME:
            desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setVersion(version);
            desiredCapabilities.setPlatform(platform);
            break;
        case PHANTOMJS:
            desiredCapabilities = DesiredCapabilities.phantomjs();
            desiredCapabilities.setVersion(version);
            desiredCapabilities.setPlatform(platform);

            break;
        case OPERA:
            desiredCapabilities = DesiredCapabilities.opera();
            desiredCapabilities.setVersion(version);
            desiredCapabilities.setPlatform(platform);
            break;
        case IE8:
            desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setVersion("8");
            desiredCapabilities.setPlatform(platform);
            break;
        case IE9:
            desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setVersion("9");
            desiredCapabilities.setPlatform(platform);
            break;
        case IE10:
            desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setVersion("10");
            desiredCapabilities.setPlatform(platform);
            break;
        case IE11:
            desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setVersion("11");
            desiredCapabilities.setPlatform(platform);
            break;
        default:
            throw new IllegalArgumentException("Unknown browser");
        }

        return desiredCapabilities;
    }
}
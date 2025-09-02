package io.jenkins.plugins.ipv4parameter;

import hudson.Extension;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import hudson.model.StringParameterValue;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.Nonnull;

public class IPv4ParameterDefinition extends ParameterDefinition {

    private final String defaultValue;

    @DataBoundConstructor
    public IPv4ParameterDefinition(String name, String description, String defaultValue) {
        super(name, description);
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String[] getDefaultSegments() {
        if (defaultValue != null && !defaultValue.isEmpty() && defaultValue.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            return defaultValue.split("\\.");
        }
        return new String[]{"192", "168", "1", "1"};
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        String octet1 = jo.getString("octet1");
        String octet2 = jo.getString("octet2");
        String octet3 = jo.getString("octet3");
        String octet4 = jo.getString("octet4");
        String value = String.format("%s.%s.%s.%s", octet1, octet2, octet3, octet4);
        return new StringParameterValue(getName(), value, getDescription());
    }

    @Override
    public ParameterValue createValue(StaplerRequest req) {
        String[] values = req.getParameterValues(getName());
        if (values == null || values.length == 0) {
            return getDefaultParameterValue();
        }
        return new StringParameterValue(getName(), values[0], getDescription());
    }

    @Override
    public StringParameterValue getDefaultParameterValue() {
        return new StringParameterValue(getName(), getDefaultValue(), getDescription());
    }

    @Extension
    @Symbol("ipv4")
    public static final class DescriptorImpl extends ParameterDescriptor {

        @Nonnull
        @Override
        public String getDisplayName() {
            return "IPv4 Parameter";
        }

        public FormValidation doCheckOctet(@QueryParameter String value) {
            if (value == null || value.trim().isEmpty()) {
                return FormValidation.error("不能为空。");
            }
            try {
                int num = Integer.parseInt(value);
                if (num < 0 || num > 255) {
                    return FormValidation.error("必须是 0 到 255 之间的数字。");
                }
                return FormValidation.ok();
            } catch (NumberFormatException e) {
                return FormValidation.error("必须是数字。");
            }
        }
    }
}
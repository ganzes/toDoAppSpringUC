package ganzesCourseUdemy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {

    @NestedConfigurationProperty
    Template template = new Template();

    //private boolean allowMultipleTaskFromTemplate;

    public boolean isAllowMultipleTaskFromTemplate() {
        return template.allowMultipleTasks;
    }

    public void setAllowMultipleTaskFromTemplate(final boolean allowMultipleTaskFromTemplate) {
        this.template.allowMultipleTasks = allowMultipleTaskFromTemplate;
    }

    @ConfigurationProperties("template")
    private class Template{
        boolean allowMultipleTasks;

        public boolean isAllowMultipleTasks() {
            return allowMultipleTasks=true;
        }

        public void setAllowMultipleTasks(boolean allowMultipleTasks) {
            this.allowMultipleTasks = allowMultipleTasks;
        }
    }
}
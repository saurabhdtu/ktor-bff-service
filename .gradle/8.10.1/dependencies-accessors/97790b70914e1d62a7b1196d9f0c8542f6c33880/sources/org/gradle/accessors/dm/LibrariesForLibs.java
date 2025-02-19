package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final KtorLibraryAccessors laccForKtorLibraryAccessors = new KtorLibraryAccessors(owner);
    private final LogbackLibraryAccessors laccForLogbackLibraryAccessors = new LogbackLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>kotlin</b>
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Group of libraries at <b>ktor</b>
     */
    public KtorLibraryAccessors getKtor() {
        return laccForKtorLibraryAccessors;
    }

    /**
     * Group of libraries at <b>logback</b>
     */
    public LogbackLibraryAccessors getLogback() {
        return laccForLogbackLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {
        private final KotlinTestLibraryAccessors laccForKotlinTestLibraryAccessors = new KotlinTestLibraryAccessors(owner);

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>kotlin.test</b>
         */
        public KotlinTestLibraryAccessors getTest() {
            return laccForKotlinTestLibraryAccessors;
        }

    }

    public static class KotlinTestLibraryAccessors extends SubDependencyFactory {

        public KotlinTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>junit</b> with <b>org.jetbrains.kotlin:kotlin-test-junit</b> coordinates and
         * with version reference <b>kotlin.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() {
            return create("kotlin.test.junit");
        }

    }

    public static class KtorLibraryAccessors extends SubDependencyFactory {
        private final KtorClientLibraryAccessors laccForKtorClientLibraryAccessors = new KtorClientLibraryAccessors(owner);
        private final KtorServerLibraryAccessors laccForKtorServerLibraryAccessors = new KtorServerLibraryAccessors(owner);

        public KtorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>ktor.client</b>
         */
        public KtorClientLibraryAccessors getClient() {
            return laccForKtorClientLibraryAccessors;
        }

        /**
         * Group of libraries at <b>ktor.server</b>
         */
        public KtorServerLibraryAccessors getServer() {
            return laccForKtorServerLibraryAccessors;
        }

    }

    public static class KtorClientLibraryAccessors extends SubDependencyFactory {
        private final KtorClientContentLibraryAccessors laccForKtorClientContentLibraryAccessors = new KtorClientContentLibraryAccessors(owner);
        private final KtorClientSerializationLibraryAccessors laccForKtorClientSerializationLibraryAccessors = new KtorClientSerializationLibraryAccessors(owner);

        public KtorClientLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>cio</b> with <b>io.ktor:ktor-client-cio</b> coordinates and
         * with version reference <b>kotlin.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCio() {
            return create("ktor.client.cio");
        }

        /**
         * Dependency provider for <b>core</b> with <b>io.ktor:ktor-client-core</b> coordinates and
         * with version reference <b>kotlin.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("ktor.client.core");
        }

        /**
         * Dependency provider for <b>logging</b> with <b>io.ktor:ktor-client-logging</b> coordinates and
         * with version reference <b>kotlin.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLogging() {
            return create("ktor.client.logging");
        }

        /**
         * Group of libraries at <b>ktor.client.content</b>
         */
        public KtorClientContentLibraryAccessors getContent() {
            return laccForKtorClientContentLibraryAccessors;
        }

        /**
         * Group of libraries at <b>ktor.client.serialization</b>
         */
        public KtorClientSerializationLibraryAccessors getSerialization() {
            return laccForKtorClientSerializationLibraryAccessors;
        }

    }

    public static class KtorClientContentLibraryAccessors extends SubDependencyFactory {

        public KtorClientContentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>negotiation</b> with <b>io.ktor:ktor-client-content-negotiation</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNegotiation() {
            return create("ktor.client.content.negotiation");
        }

    }

    public static class KtorClientSerializationLibraryAccessors extends SubDependencyFactory {

        public KtorClientSerializationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>gson</b> with <b>io.ktor:ktor-serialization-gson</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() {
            return create("ktor.client.serialization.gson");
        }

    }

    public static class KtorServerLibraryAccessors extends SubDependencyFactory {
        private final KtorServerAuthLibraryAccessors laccForKtorServerAuthLibraryAccessors = new KtorServerAuthLibraryAccessors(owner);
        private final KtorServerConfigLibraryAccessors laccForKtorServerConfigLibraryAccessors = new KtorServerConfigLibraryAccessors(owner);
        private final KtorServerContentLibraryAccessors laccForKtorServerContentLibraryAccessors = new KtorServerContentLibraryAccessors(owner);
        private final KtorServerDefaultLibraryAccessors laccForKtorServerDefaultLibraryAccessors = new KtorServerDefaultLibraryAccessors(owner);
        private final KtorServerTestLibraryAccessors laccForKtorServerTestLibraryAccessors = new KtorServerTestLibraryAccessors(owner);

        public KtorServerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>io.ktor:ktor-server-core-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("ktor.server.core");
        }

        /**
         * Dependency provider for <b>cors</b> with <b>io.ktor:ktor-server-cors-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCors() {
            return create("ktor.server.cors");
        }

        /**
         * Dependency provider for <b>netty</b> with <b>io.ktor:ktor-server-netty-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNetty() {
            return create("ktor.server.netty");
        }

        /**
         * Dependency provider for <b>openapi</b> with <b>io.ktor:ktor-server-openapi</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getOpenapi() {
            return create("ktor.server.openapi");
        }

        /**
         * Dependency provider for <b>status</b> with <b>io.ktor:ktor-server-status-pages</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStatus() {
            return create("ktor.server.status");
        }

        /**
         * Group of libraries at <b>ktor.server.auth</b>
         */
        public KtorServerAuthLibraryAccessors getAuth() {
            return laccForKtorServerAuthLibraryAccessors;
        }

        /**
         * Group of libraries at <b>ktor.server.config</b>
         */
        public KtorServerConfigLibraryAccessors getConfig() {
            return laccForKtorServerConfigLibraryAccessors;
        }

        /**
         * Group of libraries at <b>ktor.server.content</b>
         */
        public KtorServerContentLibraryAccessors getContent() {
            return laccForKtorServerContentLibraryAccessors;
        }

        /**
         * Group of libraries at <b>ktor.server.default</b>
         */
        public KtorServerDefaultLibraryAccessors getDefault() {
            return laccForKtorServerDefaultLibraryAccessors;
        }

        /**
         * Group of libraries at <b>ktor.server.test</b>
         */
        public KtorServerTestLibraryAccessors getTest() {
            return laccForKtorServerTestLibraryAccessors;
        }

    }

    public static class KtorServerAuthLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public KtorServerAuthLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>auth</b> with <b>io.ktor:ktor-server-auth-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("ktor.server.auth");
        }

        /**
         * Dependency provider for <b>jwt</b> with <b>io.ktor:ktor-server-auth-jwt-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJwt() {
            return create("ktor.server.auth.jwt");
        }

    }

    public static class KtorServerConfigLibraryAccessors extends SubDependencyFactory {

        public KtorServerConfigLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>yaml</b> with <b>io.ktor:ktor-server-config-yaml</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getYaml() {
            return create("ktor.server.config.yaml");
        }

    }

    public static class KtorServerContentLibraryAccessors extends SubDependencyFactory {

        public KtorServerContentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>negotiation</b> with <b>io.ktor:ktor-server-content-negotiation</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNegotiation() {
            return create("ktor.server.content.negotiation");
        }

    }

    public static class KtorServerDefaultLibraryAccessors extends SubDependencyFactory {

        public KtorServerDefaultLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>headers</b> with <b>io.ktor:ktor-server-default-headers-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHeaders() {
            return create("ktor.server.default.headers");
        }

    }

    public static class KtorServerTestLibraryAccessors extends SubDependencyFactory {

        public KtorServerTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>host</b> with <b>io.ktor:ktor-server-test-host-jvm</b> coordinates and
         * with version reference <b>ktor.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHost() {
            return create("ktor.server.test.host");
        }

    }

    public static class LogbackLibraryAccessors extends SubDependencyFactory {

        public LogbackLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>classic</b> with <b>ch.qos.logback:logback-classic</b> coordinates and
         * with version reference <b>logback.version</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getClassic() {
            return create("logback.classic");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgYamlLibraryAccessors laccForOrgYamlLibraryAccessors = new OrgYamlLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.yaml</b>
         */
        public OrgYamlLibraryAccessors getYaml() {
            return laccForOrgYamlLibraryAccessors;
        }

    }

    public static class OrgYamlLibraryAccessors extends SubDependencyFactory {

        public OrgYamlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>snakeyaml</b> with <b>org.yaml:snakeyaml</b> coordinates and
         * with version reference <b>snake.yaml</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSnakeyaml() {
            return create("org.yaml.snakeyaml");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final KotlinVersionAccessors vaccForKotlinVersionAccessors = new KotlinVersionAccessors(providers, config);
        private final KtorVersionAccessors vaccForKtorVersionAccessors = new KtorVersionAccessors(providers, config);
        private final LogbackVersionAccessors vaccForLogbackVersionAccessors = new LogbackVersionAccessors(providers, config);
        private final SnakeVersionAccessors vaccForSnakeVersionAccessors = new SnakeVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.kotlin</b>
         */
        public KotlinVersionAccessors getKotlin() {
            return vaccForKotlinVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.ktor</b>
         */
        public KtorVersionAccessors getKtor() {
            return vaccForKtorVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.logback</b>
         */
        public LogbackVersionAccessors getLogback() {
            return vaccForLogbackVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.snake</b>
         */
        public SnakeVersionAccessors getSnake() {
            return vaccForSnakeVersionAccessors;
        }

    }

    public static class KotlinVersionAccessors extends VersionFactory  {

        public KotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>kotlin.version</b> with value <b>2.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getVersion() { return getVersion("kotlin.version"); }

    }

    public static class KtorVersionAccessors extends VersionFactory  {

        public KtorVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>ktor.version</b> with value <b>2.3.12</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getVersion() { return getVersion("ktor.version"); }

    }

    public static class LogbackVersionAccessors extends VersionFactory  {

        public LogbackVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>logback.version</b> with value <b>1.4.14</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getVersion() { return getVersion("logback.version"); }

    }

    public static class SnakeVersionAccessors extends VersionFactory  {

        public SnakeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>snake.yaml</b> with value <b>2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getYaml() { return getVersion("snake.yaml"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final KotlinPluginAccessors paccForKotlinPluginAccessors = new KotlinPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>ktor</b> with plugin id <b>io.ktor.plugin</b> and
         * with version reference <b>ktor.version</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getKtor() { return createPlugin("ktor"); }

        /**
         * Group of plugins at <b>plugins.kotlin</b>
         */
        public KotlinPluginAccessors getKotlin() {
            return paccForKotlinPluginAccessors;
        }

    }

    public static class KotlinPluginAccessors extends PluginFactory {

        public KotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>kotlin.jvm</b> with plugin id <b>org.jetbrains.kotlin.jvm</b> and
         * with version reference <b>kotlin.version</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getJvm() { return createPlugin("kotlin.jvm"); }

    }

}

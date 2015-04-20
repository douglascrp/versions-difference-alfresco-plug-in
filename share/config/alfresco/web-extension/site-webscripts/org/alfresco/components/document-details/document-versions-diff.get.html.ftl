<#--
/**
 * Copyright (C) 2005-2012 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
 
/**
 * Copyright (C) 2012 Marco Scapoli
 *
 * This file is part of Versions Difference Alfresco Plug-in.
 *
 *  Versions Difference Alfresco Plug-in is free software: you can redistribute 
 *  it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Versions Difference Alfresco Plug-in is distributed in the hope
 *  that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Versions Difference Alfresco Plug-in.
 *  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Author  Marco Scapoli  <rianko@gmail.com>
 *  File    document-versions-diff.get.html.ftl
 **/
-->
<#if allowNewVersionUpload??>
   <#if workingCopyVersion??>
      <!-- No version component is displayed since it is a working copy -->
   <#else>
      <#assign el=args.htmlid?js_string>
      <script type="text/javascript">//<![CDATA[
      new Alfresco.DocumentVersions("${el}").setOptions(
      {
         nodeRef: "${nodeRef?js_string}",
         siteId: <#if site??>"${site?js_string}"<#else>null</#if>,
         containerId: "${container?js_string}",
         workingCopyVersion: <#if workingCopyVersion??>"${workingCopyVersion?js_string}"<#else>null</#if>,
         allowNewVersionUpload: ${allowNewVersionUpload?string}
      }).setMessages(
         ${messages}
      );
      //]]></script>

      <div id="${el}-body" class="document-versions document-details-panel">

         <h2 id="${el}-heading" class="thin dark">
            ${msg("header.versionHistory")}
            <#if allowNewVersionUpload>
               <span class="alfresco-twister-actions">
                  <a href="#" name=".onUploadNewVersionClick" class="${el} edit" title="${msg("label.newVersion")}">&nbsp;</a>
               </span>
            </#if>
         </h2>

         <div class="panel-body">

            <h3 class="thin dark">${msg("section.latestVersion")}</h3>
            <div id="${el}-latestVersion" class="current-version version-list"></div>
            <hr />
            <h3 class="thin dark">${msg("section.olderVersion")}</h3>
            <div id="${el}-olderVersions" class="version-list"></div>

         </div>

         <script type="text/javascript">//<![CDATA[
            Alfresco.util.createTwister("${el}-heading", "DocumentVersions");
         //]]></script>


      </div>
   </#if>
</#if>
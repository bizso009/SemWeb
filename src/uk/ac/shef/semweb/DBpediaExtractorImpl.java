
    

  

<!DOCTYPE html>
<html>
  <head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <script>var NREUMQ=[];NREUMQ.push(["mark","firstbyte",new Date().getTime()]);(function(){var d=document;var e=d.createElement("script");e.type="text/javascript";e.async=true;e.src="https://d1ros97qkrwjf5.cloudfront.net/8/eum/rum.js	";var s=d.getElementsByTagName("script")[0];s.parentNode.insertBefore(e,s);})()</script>
        <title>src/uk/ac/shef/semweb/DBpediaExtractorImpl.java at master from bitvaizs/SemWeb - GitHub</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />

    <link href="https://d3nwyuy0nl342s.cloudfront.net/8773c65f58af261597404a0f7801b61afc3a59d5/stylesheets/bundle_github.css" media="screen" rel="stylesheet" type="text/css" />
    

    <script type="text/javascript">
      if (typeof console == "undefined" || typeof console.log == "undefined")
        console = { log: function() {} }
    </script>
    <script type="text/javascript" charset="utf-8">
      var GitHub = {
        assetHost: 'https://d3nwyuy0nl342s.cloudfront.net'
      }
      var github_user = 'bitvaizs'
      
    </script>
    <script src="https://d3nwyuy0nl342s.cloudfront.net/8773c65f58af261597404a0f7801b61afc3a59d5/javascripts/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="https://d3nwyuy0nl342s.cloudfront.net/8773c65f58af261597404a0f7801b61afc3a59d5/javascripts/bundle_common.js" type="text/javascript"></script>
<script src="https://d3nwyuy0nl342s.cloudfront.net/8773c65f58af261597404a0f7801b61afc3a59d5/javascripts/bundle_github.js" type="text/javascript"></script>


    
    <script type="text/javascript" charset="utf-8">
      GitHub.spy({
        repo: "bitvaizs/SemWeb"
      })
    </script>

    
  <link rel='canonical' href='/bitvaizs/SemWeb/blob/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src/uk/ac/shef/semweb/DBpediaExtractorImpl.java'>

  <link href="https://github.com/bitvaizs/SemWeb/commits/master.atom" rel="alternate" title="Recent Commits to SemWeb:master" type="application/atom+xml" />

        <meta name="description" content="SemWeb hosted on GitHub" />
    <script type="text/javascript">
      GitHub.nameWithOwner = GitHub.nameWithOwner || "bitvaizs/SemWeb";
      GitHub.currentRef = 'master';
      GitHub.commitSHA = "524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be";
      GitHub.currentPath = 'src/uk/ac/shef/semweb/DBpediaExtractorImpl.java';
      GitHub.masterBranch = "master";

      
    </script>
  

        <script type="text/javascript">
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-3769691-2']);
      _gaq.push(['_setDomainName', 'none']);
      _gaq.push(['_trackPageview']);
      _gaq.push(['_trackPageLoadTime']);
      (function() {
        var ga = document.createElement('script');
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        ga.setAttribute('async', 'true');
        document.documentElement.firstChild.appendChild(ga);
      })();
    </script>

    
  </head>

  

  <body class="logged_in page-blob  windows env-production">
    

    

    

    

    

    

    <div class="subnavd" id="main">
      <div id="header" class="true">
        
          <a class="logo boring" href="https://github.com/">
            <img alt="github" class="default" src="https://d3nwyuy0nl342s.cloudfront.net/images/modules/header/logov3.png" />
            <!--[if (gt IE 8)|!(IE)]><!-->
            <img alt="github" class="hover" src="https://d3nwyuy0nl342s.cloudfront.net/images/modules/header/logov3-hover.png" />
            <!--<![endif]-->
          </a>
        
        
          





  
    <div class="userbox">
      <div class="avatarname">
        <a href="https://github.com/bitvaizs"><img src="https://secure.gravatar.com/avatar/ebab52bfce03ddbc3abe26cdcfa29d17?s=140&d=https://d3nwyuy0nl342s.cloudfront.net%2Fimages%2Fgravatars%2Fgravatar-140.png" alt="" width="20" height="20"  /></a>
        <a href="https://github.com/bitvaizs" class="name">bitvaizs</a>

        
        
      </div>
      <ul class="usernav">
        <li><a href="https://github.com/">Dashboard</a></li>
        <li>
          
          <a href="https://github.com/inbox">Inbox</a>
          <a href="https://github.com/inbox" class="unread_count ">0</a>
        </li>
        <li><a href="https://github.com/account">Account Settings</a></li>
                <li><a href="/logout">Log Out</a></li>
      </ul>
    </div><!-- /.userbox -->
  


        
        <div class="topsearch">
  
    <form action="/search" id="top_search_form" method="get">
      <a href="/search" class="advanced-search tooltipped downwards" title="Advanced Search">Advanced Search</a>
      <input type="search" class="search my_repos_autocompleter" name="q" results="5" placeholder="Search&hellip;" /> <input type="submit" value="Search" class="button" />
      <input type="hidden" name="type" value="Everything" />
      <input type="hidden" name="repo" value="" />
      <input type="hidden" name="langOverride" value="" />
      <input type="hidden" name="start_value" value="1" />
    </form>
    <ul class="nav">
      <li><a href="/explore">Explore GitHub</a></li>
      <li><a href="https://gist.github.com">Gist</a></li>
      <li><a href="/blog">Blog</a></li>
      <li><a href="http://help.github.com">Help</a></li>
    </ul>
  
</div>

      </div>

      
      
        
    <div class="site">
      <div class="pagehead repohead vis-public    instapaper_ignore readability-menu">

      

      <div class="title-actions-bar">
        <h1>
          <a href="/bitvaizs">bitvaizs</a> / <strong><a href="/bitvaizs/SemWeb">SemWeb</a></strong>
          
          
        </h1>

        
    <ul class="actions">
      

      
        <li class="for-owner" style="display:none"><a href="/bitvaizs/SemWeb/admin" class="minibutton btn-admin "><span><span class="icon"></span>Admin</span></a></li>
        <li>
          <a href="/bitvaizs/SemWeb/toggle_watch" class="minibutton btn-watch " id="watch_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', 'd686c03348ba3f1a8be1e6f193b2a7f3d14cc49c'); f.appendChild(s);f.submit();return false;" style="display:none"><span><span class="icon"></span>Watch</span></a>
          <a href="/bitvaizs/SemWeb/toggle_watch" class="minibutton btn-watch " id="unwatch_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', 'd686c03348ba3f1a8be1e6f193b2a7f3d14cc49c'); f.appendChild(s);f.submit();return false;" style="display:none"><span><span class="icon"></span>Unwatch</span></a>
        </li>
        
          
            <li class="for-notforked" style="display:none"><a href="/bitvaizs/SemWeb/fork" class="minibutton btn-fork " id="fork_button" onclick="var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', 'd686c03348ba3f1a8be1e6f193b2a7f3d14cc49c'); f.appendChild(s);f.submit();return false;"><span><span class="icon"></span>Fork</span></a></li>
            <li class="for-hasfork" style="display:none"><a href="#" class="minibutton btn-fork " id="your_fork_button"><span><span class="icon"></span>Your Fork</span></a></li>
          

          <li id='pull_request_item' class='nspr' style='display:none'><a href="/bitvaizs/SemWeb/pull/new/master" class="minibutton btn-pull-request "><span><span class="icon"></span>Pull Request</span></a></li>
        
      
      
      <li class="repostats">
        <ul class="repo-stats">
          <li class="watchers"><a href="/bitvaizs/SemWeb/watchers" title="Watchers" class="tooltipped downwards">1</a></li>
          <li class="forks"><a href="/bitvaizs/SemWeb/network" title="Forks" class="tooltipped downwards">1</a></li>
        </ul>
      </li>
    </ul>

      </div>

        
  <ul class="tabs">
    <li><a href="/bitvaizs/SemWeb" class="selected" highlight="repo_source">Source</a></li>
    <li><a href="/bitvaizs/SemWeb/commits/master" highlight="repo_commits">Commits</a></li>
    <li><a href="/bitvaizs/SemWeb/network" highlight="repo_network">Network</a></li>
    <li><a href="/bitvaizs/SemWeb/pulls" highlight="repo_pulls">Pull Requests (0)</a></li>

    
      <li><a href="/bitvaizs/SemWeb/forkqueue" highlight="repo_fork_queue">Fork Queue</a></li>
    

    
      
      <li><a href="/bitvaizs/SemWeb/issues" highlight="issues">Issues (0)</a></li>
    

                <li><a href="/bitvaizs/SemWeb/wiki" highlight="repo_wiki">Wiki (0)</a></li>
        
    <li><a href="/bitvaizs/SemWeb/graphs" highlight="repo_graphs">Graphs</a></li>

    <li class="contextswitch nochoices">
      <span class="toggle leftwards" >
        <em>Branch:</em>
        <code>master</code>
      </span>
    </li>
  </ul>

  <div style="display:none" id="pl-description"><p><em class="placeholder">click here to add a description</em></p></div>
  <div style="display:none" id="pl-homepage"><p><em class="placeholder">click here to add a homepage</em></p></div>

  <div class="subnav-bar">
  
  <ul>
    <li>
      
      <a href="/bitvaizs/SemWeb/branches" class="dropdown">Switch Branches (1)</a>
      <ul>
        
          
            <li><strong>master &#x2713;</strong></li>
            
      </ul>
    </li>
    <li>
      <a href="#" class="dropdown defunct">Switch Tags (0)</a>
      
    </li>
    <li>
    
    <a href="/bitvaizs/SemWeb/branches" class="manage">Branch List</a>
    
    </li>
  </ul>
</div>

  
  
  
  
  
  



        
    <div id="repo_details" class="metabox clearfix">
      <div id="repo_details_loader" class="metabox-loader" style="display:none">Sending Request&hellip;</div>

        <a href="/bitvaizs/SemWeb/downloads" class="download-source" id="download_button" title="Download source, tagged packages and binaries."><span class="icon"></span>Downloads</a>

      <div id="repository_desc_wrapper">
      <div id="repository_description" rel="repository_description_edit">
        
      </div>

      <div id="repository_description_edit" style="display:none;" class="inline-edit">
        <form action="/bitvaizs/SemWeb/admin/update" method="post"><div style="margin:0;padding:0"><input name="authenticity_token" type="hidden" value="d686c03348ba3f1a8be1e6f193b2a7f3d14cc49c" /></div>
          <input type="hidden" name="field" value="repository_description">
          <input type="text" class="textfield" name="value" value="">
          <div class="form-actions">
            <button class="minibutton"><span>Save</span></button> &nbsp; <a href="#" class="cancel">Cancel</a>
          </div>
        </form>
      </div>

      
      <div class="repository-homepage" id="repository_homepage" rel="repository_homepage_edit">
        <p><a href="https://staffwww.dcs.shef.ac.uk/xzprxz/F.Ciravegna/campus_only/COM4280/Assignment/2010-2011/AssignmentPart2.pdf" rel="nofollow">https://staffwww.dcs.shef.ac.uk/xzprxz/F.Ciravegna/campus_only/COM4280/Assignment/2010-2011/AssignmentPart2.pdf</a></p>
      </div>

      <div id="repository_homepage_edit" style="display:none;" class="inline-edit">
        <form action="/bitvaizs/SemWeb/admin/update" method="post"><div style="margin:0;padding:0"><input name="authenticity_token" type="hidden" value="d686c03348ba3f1a8be1e6f193b2a7f3d14cc49c" /></div>
          <input type="hidden" name="field" value="repository_homepage">
          <input type="text" class="textfield" name="value" value="https://staffwww.dcs.shef.ac.uk/xzprxz/F.Ciravegna/campus_only/COM4280/Assignment/2010-2011/AssignmentPart2.pdf">
          <div class="form-actions">
            <button class="minibutton"><span>Save</span></button> &nbsp; <a href="#" class="cancel">Cancel</a>
          </div>
        </form>
      </div>
      </div>
      <div class="rule "></div>
      <div id="url_box" class="url-box">
  

  <ul class="clone-urls">
    
      
        <li id="private_clone_url"><a href="git@github.com:bitvaizs/SemWeb.git" data-permissions="Read+Write">SSH</a></li>
      
      <li id="http_clone_url"><a href="https://bitvaizs@github.com/bitvaizs/SemWeb.git" data-permissions="Read+Write">HTTP</a></li>
      <li id="public_clone_url"><a href="git://github.com/bitvaizs/SemWeb.git" data-permissions="Read-Only">Git Read-Only</a></li>
    
    
  </ul>
  <input type="text" spellcheck="false" id="url_field" class="url-field" />
        <span style="display:none" id="url_box_clippy"></span>
      <span id="clippy_tooltip_url_box_clippy" class="clippy-tooltip tooltipped" title="copy to clipboard">
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
              width="14"
              height="14"
              class="clippy"
              id="clippy" >
      <param name="movie" value="https://d3nwyuy0nl342s.cloudfront.net/flash/clippy.swf?v5"/>
      <param name="allowScriptAccess" value="always" />
      <param name="quality" value="high" />
      <param name="scale" value="noscale" />
      <param NAME="FlashVars" value="id=url_box_clippy&amp;copied=&amp;copyto=">
      <param name="bgcolor" value="#FFFFFF">
      <param name="wmode" value="opaque">
      <embed src="https://d3nwyuy0nl342s.cloudfront.net/flash/clippy.swf?v5"
             width="14"
             height="14"
             name="clippy"
             quality="high"
             allowScriptAccess="always"
             type="application/x-shockwave-flash"
             pluginspage="http://www.macromedia.com/go/getflashplayer"
             FlashVars="id=url_box_clippy&amp;copied=&amp;copyto="
             bgcolor="#FFFFFF"
             wmode="opaque"
      />
      </object>
      </span>

  <p id="url_description"><strong>Read+Write</strong> access</p>
</div>
    </div>

    <div class="frame frame-center tree-finder" style="display:none">
      <div class="breadcrumb">
        <b><a href="/bitvaizs/SemWeb">SemWeb</a></b> /
        <input class="tree-finder-input" type="text" name="query" autocomplete="off" spellcheck="false">
      </div>

      
        <div class="octotip">
          <p>
            <a href="/bitvaizs/SemWeb/dismiss-tree-finder-help" class="dismiss js-dismiss-tree-list-help" title="Hide this notice forever">Dismiss</a>
            <strong>Octotip:</strong> You've activated the <em>file finder</em> by pressing <span class="kbd">t</span>
            Start typing to filter the file list. Use <span class="kbd badmono">↑</span> and <span class="kbd badmono">↓</span> to navigate,
            <span class="kbd">enter</span> to view files.
          </p>
        </div>
      

      <table class="tree-browser" cellpadding="0" cellspacing="0">
        <tr class="js-header"><th>&nbsp;</th><th>name</th></tr>
        <tr class="js-no-results no-results" style="display: none">
          <th colspan="2">No matching files</th>
        </tr>
        <tbody class="js-results-list">
        </tbody>
      </table>
    </div>

    <div id="jump-to-line" style="display:none">
      <h2>Jump to Line</h2>
      <form>
        <input class="textfield" type="text">
        <div class="full-button">
          <button type="submit" class="classy">
            <span>Go</span>
          </button>
        </div>
      </form>
    </div>


        

      </div><!-- /.pagehead -->

      

  







<script type="text/javascript">
  GitHub.downloadRepo = '/bitvaizs/SemWeb/archives/master'
  GitHub.revType = "master"

  GitHub.repoName = "SemWeb"
  GitHub.controllerName = "blob"
  GitHub.actionName     = "show"
  GitHub.currentAction  = "blob#show"

  
    GitHub.loggedIn = true
    GitHub.hasWriteAccess = true
    GitHub.hasAdminAccess = true
    GitHub.watchingRepo = true
    GitHub.ignoredRepo = false
    GitHub.hasForkOfRepo = null
    GitHub.hasForked = true
  

  
</script>






<div class="flash-messages"></div>


  <div id="commit">
    <div class="group">
        
  <div class="envelope commit">
    <div class="human">
      
        <div class="message"><pre><a href="/bitvaizs/SemWeb/commit/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be">DBExtractor tests</a> </pre></div>
      

      <div class="actor">
        <div class="gravatar">
          
          <img src="https://secure.gravatar.com/avatar/9f75a2c0e6b8581b7bd01ed4e9daa315?s=140&d=https://d3nwyuy0nl342s.cloudfront.net%2Fimages%2Fgravatars%2Fgravatar-140.png" alt="" width="30" height="30"  />
        </div>
        <div class="name"><a href="/kushaldsouza">kushaldsouza</a> <span>(author)</span></div>
        <div class="date">
          <abbr class="relatize" title="2011-05-19 01:06:35">Thu May 19 01:06:35 -0700 2011</abbr>
        </div>
      </div>

      

    </div>
    <div class="machine">
      <span>c</span>ommit&nbsp;&nbsp;<a href="/bitvaizs/SemWeb/commit/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be" hotkey="c">524bd7d12f5d99cc0fbb</a><br />
      <span>t</span>ree&nbsp;&nbsp;&nbsp;&nbsp;<a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be" hotkey="t">7bf4bf93460ae213fa54</a><br />
      
        <span>p</span>arent&nbsp;
        
        <a href="/bitvaizs/SemWeb/tree/1a0707d6d0838163b98ac224580c8b59b0306c60" hotkey="p">1a0707d6d0838163b98a</a>
      

    </div>
  </div>

    </div>
  </div>



  <div id="slider">

  

    <div class="breadcrumb" data-path="src/uk/ac/shef/semweb/DBpediaExtractorImpl.java/">
      <b><a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be">SemWeb</a></b> / <a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src">src</a> / <a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src/uk">uk</a> / <a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src/uk/ac">ac</a> / <a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src/uk/ac/shef">shef</a> / <a href="/bitvaizs/SemWeb/tree/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src/uk/ac/shef/semweb">semweb</a> / DBpediaExtractorImpl.java       <span style="display:none" id="clippy_460">src/uk/ac/shef/semweb/DBpediaExtractorImpl.java</span>
      
      <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
              width="110"
              height="14"
              class="clippy"
              id="clippy" >
      <param name="movie" value="https://d3nwyuy0nl342s.cloudfront.net/flash/clippy.swf?v5"/>
      <param name="allowScriptAccess" value="always" />
      <param name="quality" value="high" />
      <param name="scale" value="noscale" />
      <param NAME="FlashVars" value="id=clippy_460&amp;copied=copied!&amp;copyto=copy to clipboard">
      <param name="bgcolor" value="#FFFFFF">
      <param name="wmode" value="opaque">
      <embed src="https://d3nwyuy0nl342s.cloudfront.net/flash/clippy.swf?v5"
             width="110"
             height="14"
             name="clippy"
             quality="high"
             allowScriptAccess="always"
             type="application/x-shockwave-flash"
             pluginspage="http://www.macromedia.com/go/getflashplayer"
             FlashVars="id=clippy_460&amp;copied=copied!&amp;copyto=copy to clipboard"
             bgcolor="#FFFFFF"
             wmode="opaque"
      />
      </object>
      

    </div>

    <div class="frames">
      <div class="frame frame-center" data-path="src/uk/ac/shef/semweb/DBpediaExtractorImpl.java/" data-canonical-url="/bitvaizs/SemWeb/blob/524bd7d12f5d99cc0fbb4fd84d54e9bd12c0b9be/src/uk/ac/shef/semweb/DBpediaExtractorImpl.java">
        
          <ul class="big-actions">
            
            <li><a class="file-edit-link minibutton" href="/bitvaizs/SemWeb/file-edit/__current_ref__/src/uk/ac/shef/semweb/DBpediaExtractorImpl.java"><span>Edit this file</span></a></li>
          </ul>
        

        <div id="files">
          <div class="file">
            <div class="meta">
              <div class="info">
                <span class="icon"><img alt="Txt" height="16" src="https://d3nwyuy0nl342s.cloudfront.net/images/icons/txt.png" width="16" /></span>
                <span class="mode" title="File Mode">100644</span>
                
                  <span>42 lines (35 sloc)</span>
                
                <span>1.158 kb</span>
              </div>
              <ul class="actions">
                <li><a href="/bitvaizs/SemWeb/raw/master/src/uk/ac/shef/semweb/DBpediaExtractorImpl.java" id="raw-url">raw</a></li>
                
                  <li><a href="/bitvaizs/SemWeb/blame/master/src/uk/ac/shef/semweb/DBpediaExtractorImpl.java">blame</a></li>
                
                <li><a href="/bitvaizs/SemWeb/commits/master/src/uk/ac/shef/semweb/DBpediaExtractorImpl.java">history</a></li>
              </ul>
            </div>
            
  <div class="data type-java">
    
      <table cellpadding="0" cellspacing="0">
        <tr>
          <td>
            <pre class="line_numbers"><span id="L1" rel="#L1">1</span>
<span id="L2" rel="#L2">2</span>
<span id="L3" rel="#L3">3</span>
<span id="L4" rel="#L4">4</span>
<span id="L5" rel="#L5">5</span>
<span id="L6" rel="#L6">6</span>
<span id="L7" rel="#L7">7</span>
<span id="L8" rel="#L8">8</span>
<span id="L9" rel="#L9">9</span>
<span id="L10" rel="#L10">10</span>
<span id="L11" rel="#L11">11</span>
<span id="L12" rel="#L12">12</span>
<span id="L13" rel="#L13">13</span>
<span id="L14" rel="#L14">14</span>
<span id="L15" rel="#L15">15</span>
<span id="L16" rel="#L16">16</span>
<span id="L17" rel="#L17">17</span>
<span id="L18" rel="#L18">18</span>
<span id="L19" rel="#L19">19</span>
<span id="L20" rel="#L20">20</span>
<span id="L21" rel="#L21">21</span>
<span id="L22" rel="#L22">22</span>
<span id="L23" rel="#L23">23</span>
<span id="L24" rel="#L24">24</span>
<span id="L25" rel="#L25">25</span>
<span id="L26" rel="#L26">26</span>
<span id="L27" rel="#L27">27</span>
<span id="L28" rel="#L28">28</span>
<span id="L29" rel="#L29">29</span>
<span id="L30" rel="#L30">30</span>
<span id="L31" rel="#L31">31</span>
<span id="L32" rel="#L32">32</span>
<span id="L33" rel="#L33">33</span>
<span id="L34" rel="#L34">34</span>
<span id="L35" rel="#L35">35</span>
<span id="L36" rel="#L36">36</span>
<span id="L37" rel="#L37">37</span>
<span id="L38" rel="#L38">38</span>
<span id="L39" rel="#L39">39</span>
<span id="L40" rel="#L40">40</span>
<span id="L41" rel="#L41">41</span>
<span id="L42" rel="#L42">42</span>
</pre>
          </td>
          <td width="100%">
            
              
                <div class="highlight"><pre><div class='line' id='LC1'><span class="kn">package</span> <span class="n">uk</span><span class="o">.</span><span class="na">ac</span><span class="o">.</span><span class="na">shef</span><span class="o">.</span><span class="na">semweb</span><span class="o">;</span></div><div class='line' id='LC2'><br/></div><div class='line' id='LC3'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.query.Query</span><span class="o">;</span></div><div class='line' id='LC4'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.query.QueryExecution</span><span class="o">;</span></div><div class='line' id='LC5'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.query.QueryExecutionFactory</span><span class="o">;</span></div><div class='line' id='LC6'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.query.QueryFactory</span><span class="o">;</span></div><div class='line' id='LC7'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.query.ResultSet</span><span class="o">;</span></div><div class='line' id='LC8'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.query.ResultSetFormatter</span><span class="o">;</span></div><div class='line' id='LC9'><span class="kn">import</span> <span class="nn">com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP</span><span class="o">;</span></div><div class='line' id='LC10'><br/></div><div class='line' id='LC11'><span class="kd">public</span> <span class="kd">class</span> <span class="nc">DBpediaExtractorImpl</span> <span class="kd">extends</span> <span class="n">XMLExtractorImpl</span> <span class="kd">implements</span> <span class="n">DBpediaExtractor</span> <span class="o">{</span></div><div class='line' id='LC12'><br/></div><div class='line' id='LC13'>	<span class="kd">private</span> <span class="n">String</span> <span class="n">service</span> <span class="o">=</span> <span class="s">&quot;http://dbpedia.org/sparql&quot;</span><span class="o">;</span></div><div class='line' id='LC14'><br/></div><div class='line' id='LC15'>	<span class="nd">@Override</span></div><div class='line' id='LC16'>	<span class="kd">public</span> <span class="kt">void</span> <span class="nf">getDBpediaInfo</span><span class="o">(</span><span class="n">String</span> <span class="n">URL</span><span class="o">)</span> <span class="o">{</span></div><div class='line' id='LC17'>		<span class="c1">// TODO Auto-generated method stub</span></div><div class='line' id='LC18'>	<span class="o">}</span></div><div class='line' id='LC19'><br/></div><div class='line' id='LC20'>	<span class="kd">public</span> <span class="kt">void</span> <span class="nf">getProperty</span><span class="o">()</span></div><div class='line' id='LC21'>	<span class="o">{</span></div><div class='line' id='LC22'>		<span class="n">String</span> <span class="n">sparqlQueryString</span><span class="o">=</span> <span class="s">&quot;Query goes here&quot;</span><span class="o">;</span></div><div class='line' id='LC23'>		<span class="k">try</span></div><div class='line' id='LC24'>		<span class="o">{</span></div><div class='line' id='LC25'>			<span class="n">runQuery</span><span class="o">(</span><span class="n">sparqlQueryString</span><span class="o">);</span></div><div class='line' id='LC26'>		<span class="o">}</span><span class="k">catch</span><span class="o">(</span><span class="n">QueryExceptionHTTP</span> <span class="n">e</span><span class="o">)</span></div><div class='line' id='LC27'>		<span class="o">{</span></div><div class='line' id='LC28'>			<span class="n">System</span><span class="o">.</span><span class="na">out</span><span class="o">.</span><span class="na">println</span><span class="o">(</span><span class="k">this</span><span class="o">.</span><span class="na">service</span> <span class="o">+</span> <span class="s">&quot; is DOWN&quot;</span><span class="o">);</span></div><div class='line' id='LC29'>		<span class="o">}</span></div><div class='line' id='LC30'>	<span class="o">}</span></div><div class='line' id='LC31'><br/></div><div class='line' id='LC32'>	<span class="kd">public</span> <span class="n">ResultSet</span> <span class="nf">runQuery</span><span class="o">(</span><span class="n">String</span> <span class="n">queryString</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">QueryExceptionHTTP</span></div><div class='line' id='LC33'>	<span class="o">{</span></div><div class='line' id='LC34'>		<span class="n">Query</span> <span class="n">query</span> <span class="o">=</span> <span class="n">QueryFactory</span><span class="o">.</span><span class="na">create</span><span class="o">(</span><span class="n">queryString</span><span class="o">);</span></div><div class='line' id='LC35'>		<span class="n">QueryExecution</span> <span class="n">qexec</span> <span class="o">=</span> <span class="n">QueryExecutionFactory</span><span class="o">.</span><span class="na">sparqlService</span><span class="o">(</span><span class="k">this</span><span class="o">.</span><span class="na">service</span><span class="o">,</span> <span class="n">query</span><span class="o">);</span></div><div class='line' id='LC36'>		<span class="n">ResultSet</span> <span class="n">results</span> <span class="o">=</span> <span class="n">qexec</span><span class="o">.</span><span class="na">execSelect</span><span class="o">();</span></div><div class='line' id='LC37'>		<span class="n">ResultSetFormatter</span><span class="o">.</span><span class="na">out</span><span class="o">(</span><span class="n">System</span><span class="o">.</span><span class="na">out</span><span class="o">,</span> <span class="n">results</span><span class="o">,</span> <span class="n">query</span><span class="o">);</span></div><div class='line' id='LC38'>		<span class="n">qexec</span><span class="o">.</span><span class="na">close</span><span class="o">();</span></div><div class='line' id='LC39'>		<span class="k">return</span> <span class="n">results</span><span class="o">;</span>		</div><div class='line' id='LC40'>	<span class="o">}</span></div><div class='line' id='LC41'><span class="o">}</span></div><div class='line' id='LC42'><br/></div></pre></div>
              
            
          </td>
        </tr>
      </table>
    
  </div>


          </div>
        </div>
      </div>
    </div>
  

  </div>


<div class="frame frame-loading" style="display:none;">
  <img src="https://d3nwyuy0nl342s.cloudfront.net/images/modules/ajax/big_spinner_336699.gif" height="32" width="32">
</div>

    </div>
  
      
    </div>

    <div id="footer" class="clearfix">
      <div class="site">
        <div class="sponsor">
          <a href="http://www.rackspace.com" class="logo">
            <img alt="Dedicated Server" height="36" src="https://d3nwyuy0nl342s.cloudfront.net/images/modules/footer/rackspace_logo.png?v2" width="38" />
          </a>
          Powered by the <a href="http://www.rackspace.com ">Dedicated
          Servers</a> and<br/> <a href="http://www.rackspacecloud.com">Cloud
          Computing</a> of Rackspace Hosting<span>&reg;</span>
        </div>

        <ul class="links">
          <li class="blog"><a href="https://github.com/blog">Blog</a></li>
          <li><a href="https://github.com/contact">Support</a></li>
          <li><a href="https://github.com/training">Training</a></li>
          <li><a href="http://jobs.github.com">Job Board</a></li>
          <li><a href="http://shop.github.com">Shop</a></li>
          <li><a href="https://github.com/contact">Contact</a></li>
          <li><a href="http://developer.github.com">API</a></li>
          <li><a href="http://status.github.com">Status</a></li>
        </ul>
        <ul class="sosueme">
          <li class="main">&copy; 2011 <span id="_rrt" title="0.22404s from fe5.rs.github.com">GitHub</span> Inc. All rights reserved.</li>
          <li><a href="/site/terms">Terms of Service</a></li>
          <li><a href="/site/privacy">Privacy</a></li>
          <li><a href="https://github.com/security">Security</a></li>
        </ul>
      </div>
    </div><!-- /#footer -->

    <script>window._auth_token = "d686c03348ba3f1a8be1e6f193b2a7f3d14cc49c"</script>
    

<div id="keyboard_shortcuts_pane" class="instapaper_ignore readability-extra" style="display:none">
  <h2>Keyboard Shortcuts <small><a href="#" class="js-see-all-keyboard-shortcuts">(see all)</a></small></h2>

  <div class="columns threecols">
    <div class="column first">
      <h3>Site wide shortcuts</h3>
      <dl class="keyboard-mappings">
        <dt>s</dt>
        <dd>Focus site search</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>?</dt>
        <dd>Bring up this help dialog</dd>
      </dl>
    </div><!-- /.column.first -->

    <div class="column middle" style='display:none'>
      <h3>Commit list</h3>
      <dl class="keyboard-mappings">
        <dt>j</dt>
        <dd>Move selected down</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>k</dt>
        <dd>Move selected up</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>t</dt>
        <dd>Open tree</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>p</dt>
        <dd>Open parent</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>c <em>or</em> o <em>or</em> enter</dt>
        <dd>Open commit</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>y</dt>
        <dd>Expand URL to its canonical form</dd>
      </dl>
    </div><!-- /.column.first -->

    <div class="column last" style='display:none'>
      <h3>Pull request list</h3>
      <dl class="keyboard-mappings">
        <dt>j</dt>
        <dd>Move selected down</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>k</dt>
        <dd>Move selected up</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>o <em>or</em> enter</dt>
        <dd>Open issue</dd>
      </dl>
    </div><!-- /.columns.last -->

  </div><!-- /.columns.equacols -->

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Issues</h3>

    <div class="columns threecols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt>j</dt>
          <dd>Move selected down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>k</dt>
          <dd>Move selected up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>x</dt>
          <dd>Toggle select target</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>o <em>or</em> enter</dt>
          <dd>Open issue</dd>
        </dl>
      </div><!-- /.column.first -->
      <div class="column middle">
        <dl class="keyboard-mappings">
          <dt>I</dt>
          <dd>Mark selected as read</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>U</dt>
          <dd>Mark selected as unread</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>e</dt>
          <dd>Close selected</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>y</dt>
          <dd>Remove selected from view</dd>
        </dl>
      </div><!-- /.column.middle -->
      <div class="column last">
        <dl class="keyboard-mappings">
          <dt>c</dt>
          <dd>Create issue</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>l</dt>
          <dd>Create label</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>i</dt>
          <dd>Back to inbox</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>u</dt>
          <dd>Back to issues</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>/</dt>
          <dd>Focus issues search</dd>
        </dl>
      </div>
    </div>
  </div>

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Network Graph</h3>
    <div class="columns equacols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt><span class="badmono">←</span> <em>or</em> h</dt>
          <dd>Scroll left</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">→</span> <em>or</em> l</dt>
          <dd>Scroll right</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">↑</span> <em>or</em> k</dt>
          <dd>Scroll up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">↓</span> <em>or</em> j</dt>
          <dd>Scroll down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>t</dt>
          <dd>Toggle visibility of head labels</dd>
        </dl>
      </div><!-- /.column.first -->
      <div class="column last">
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">←</span> <em>or</em> shift h</dt>
          <dd>Scroll all the way left</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">→</span> <em>or</em> shift l</dt>
          <dd>Scroll all the way right</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">↑</span> <em>or</em> shift k</dt>
          <dd>Scroll all the way up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">↓</span> <em>or</em> shift j</dt>
          <dd>Scroll all the way down</dd>
        </dl>
      </div><!-- /.column.last -->
    </div>
  </div>

  <div >
    <div class="rule"></div>
    <div class="columns threecols">
      <div class="column first" >
        <h3>Source Code Browsing</h3>
        <dl class="keyboard-mappings">
          <dt>t</dt>
          <dd>Activates the file finder</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>l</dt>
          <dd>Jump to line</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>y</dt>
          <dd>Expand URL to its canonical form</dd>
        </dl>
      </div>
    </div>
  </div>
</div>

    

    <!--[if IE 8]>
    <script type="text/javascript" charset="utf-8">
      $(document.body).addClass("ie8")
    </script>
    <![endif]-->

    <!--[if IE 7]>
    <script type="text/javascript" charset="utf-8">
      $(document.body).addClass("ie7")
    </script>
    <![endif]-->

    
    <script type='text/javascript'></script>
    
    <script type="text/javascript" charset="utf-8">NREUMQ.push(["nrf2","beacon-1.newrelic.com","2f94e4d8c2",64799,"dw1bEBZcX1RWRhoSFFEHGhcMXEQ=",0,289])</script>
  </body>
</html>

